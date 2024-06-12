package com.example.ehasibu.product.model

import Add_Product
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ehasibu.databinding.FragmentProductBinding
import com.example.ehasibu.product.data.Adapter
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

const val TAG = "product"

class Product : Fragment() {

    companion object {
        fun newInstance() = Product()
    }

    private lateinit var binding: FragmentProductBinding
    private lateinit var adapter: Adapter


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)

        val pref = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val apiToken = pref.getString(API_TOKEN, "")

        val repo = ProductRepository(apiToken!!)

        val factory: ProductProvider by lazy {
            ProductProvider(repo)
        }

        // Initialize the ViewModel using the ViewModelProvider
        val productViewModel: ProductViewModel by viewModels {
            factory
        }

        productViewModel.products.observe(viewLifecycleOwner) { products ->
            Log.d(TAG, "onCreateView: $products")
            adapter = Adapter(products)
            binding.recyclerView.adapter = adapter
        }

        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")

        // Initialize RecyclerView with a LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        binding.addProductBtn.setOnClickListener {
            Log.d(TAG, "show us our frame")
            val fragment = Add_Product.newInstance()
            val trans = childFragmentManager.beginTransaction()
            trans.replace(binding.frameLayout.id, fragment).commit()
            binding.frameLayout.visibility = View.VISIBLE
        }

        binding.setPriceBtn.setOnClickListener {
        }


        return binding.root
    }

}
