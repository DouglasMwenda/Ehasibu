import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.ehasibu.databinding.FragmentAddProductBinding
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.data.ProductRequest
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.AddProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG = "addproduct"

class AddProduct : DialogFragment() {

    private lateinit var binding: FragmentAddProductBinding

    private val viewModel: AddProductViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        AddProductViewModel.AddProductProvider(ProductRepository(token))
    }

    companion object {
        fun newInstance() = AddProduct()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)

        viewModel.isProductAdded.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess == true) {
                dismiss()
            }
        })

        binding.saveProductBtn.setOnClickListener {
            val product = ProductRequest(
                productName = binding.productname.text.toString(),
                description = binding.description.text.toString(),
                category = binding.category.text.toString(),
                unit = binding.unit.text.toString(),
            )
            viewModel.addProduct(product)

        }

        binding.cancelProductButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
