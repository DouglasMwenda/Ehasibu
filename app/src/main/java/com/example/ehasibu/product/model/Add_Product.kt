
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentAddProductBinding
import com.example.ehasibu.product.viewmodel.AddProductViewModel

class Add_Product : Fragment() {

    private lateinit var binding: FragmentAddProductBinding

    private val viewModel: AddProductViewModel by viewModels()

    companion object {
        fun newInstance() = Add_Product()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
    //}

    //override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    //    super.onViewCreated(view, savedInstanceState)

       // saveProductButton = binding.saveProductButton


        binding.saveProductBtn.setOnClickListener {



        }
        binding.cancelProductButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root

    }
}
