
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentAddProductBinding
import com.example.ehasibu.product.data.ProductRequest
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.AddProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG = "addproduct"

class Add_Product : DialogFragment() {

    private lateinit var binding: FragmentAddProductBinding

    private val viewModel: AddProductViewModel by viewModels {
        AddProductViewModel(ProductRepository(getAPIAuthToken()))
    }

    companion object {
        fun newInstance() = Add_Product()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)

        binding.cancelProductButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            requireActivity().onBackPressed()
        }
    }

    private fun getAPIAuthToken(): String {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return sharedPrefs.getString(API_TOKEN, "")?: ""
    }
}
