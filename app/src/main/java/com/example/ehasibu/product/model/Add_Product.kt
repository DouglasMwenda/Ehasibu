
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.ehasibu.databinding.FragmentAddProductBinding

class Add_Product : DialogFragment() {

    private lateinit var binding: FragmentAddProductBinding


    companion object {
        fun newInstance() = Add_Product()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)


        binding.saveProductBtn.setOnClickListener {





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
