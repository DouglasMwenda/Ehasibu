import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentAddProductBinding
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.data.ProductRequest
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG = "AddProduct"

class AddProduct : DialogFragment() {

    private lateinit var binding: FragmentAddProductBinding
    private var editRequest: EditRequest? = null

    private val viewModel: AddProductViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")
            ?: throw IllegalStateException("API Token is missing")
        AddProductViewModel.AddProductProvider(ProductRepository(token))
    }

    companion object {
        private const val ARG_EDIT_REQUEST = "edit_request"

        fun newInstance(editRequest: EditRequest? = null): AddProduct {
            val fragment = AddProduct()
            val args = Bundle().apply {
                putParcelable(ARG_EDIT_REQUEST, editRequest)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            editRequest = it.getParcelable(ARG_EDIT_REQUEST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)

        viewModel.isProductAdded.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess == true) {
                Toast.makeText(context, "Product saved successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "Failed to save product", Toast.LENGTH_SHORT).show()
            }
        }

        binding.saveProductBtn.setOnClickListener {
            if (validateInput()) {
                if (editRequest == null) {
                    val product = ProductRequest(
                        productName = binding.productname.text.toString(),
                        description = binding.description.text.toString(),
                        category = binding.category.text.toString(),
                        unit = binding.unit.text.toString()
                    )
                    viewModel.addProduct(product)
                } else {
                    val product = EditRequest(
                        productId = editRequest!!.productId,
                        productName = binding.productname.text.toString(),
                        description = binding.description.text.toString(),
                        category = binding.category.text.toString(),
                        unit = binding.unit.text.toString()
                    )
                    viewModel.updateProduct(product)
                }
            }
        }

        binding.cancelProductButton.setOnClickListener {
            dismiss()
        }

        editRequest?.let {
            binding.productname.setText(it.productName)
            binding.description.setText(it.description)
            binding.category.setText(it.category)
            binding.unit.setText(it.unit)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun validateInput(): Boolean {
        return when {
            binding.productname.text.isNullOrBlank() -> {
                Toast.makeText(context, "Product name is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.description.text.isNullOrBlank() -> {
                Toast.makeText(context, "Description is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.category.text.isNullOrBlank() -> {
                Toast.makeText(context, "Category is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.unit.text.isNullOrBlank() -> {
                Toast.makeText(context, "Unit is required", Toast.LENGTH_SHORT).show()
                false
            }

            else -> true
        }
    }
}
