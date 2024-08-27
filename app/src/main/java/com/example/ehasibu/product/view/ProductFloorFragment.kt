package com.example.ehasibu.product.view
/*
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ehasibu.databinding.ProductsfloorBinding
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG1 = "params"
class ProductFloorFragment: Fragment() {
    private lateinit var binding: ProductsfloorBinding
    val args: ProductFloorFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ProductsfloorBinding.inflate(inflater, container, false)
        val productId = args.productId
        val pref = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val apiToken = pref.getString(API_TOKEN, "")

        val repo = ProductRepository(apiToken!!)

        // Initialize the ViewModel using the ViewModelProvider
        val productViewModel: ProductViewModel by viewModels {
            ProductProvider(repo)
        }

        // Observe the products LiveData
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            val realProduct = products?.firstOrNull { it.productId == productId }
            if (realProduct != null) {
                Log.d(TAG1, realProduct.toString())
                // Update the UI with realProduct
            } else {
                Log.d(TAG1, "Product not found")
            }
        }

        Log.d(TAG1, productId.toString())
        return binding.root
    }
}*/
