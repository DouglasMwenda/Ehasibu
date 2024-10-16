package com.example.ehasibu.product.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentSetpriceBinding
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

class SetPrice : DialogFragment() {
    private lateinit var binding: FragmentSetpriceBinding
    private lateinit var productname: AutoCompleteTextView

    private val productViewModel: ProductViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "") ?: ""
        val repo = ProductRepository(token)
        ProductProvider(repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetpriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productname = binding.productName

        // Observe the products LiveData
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            products?.let { productList ->
                // Update the AutoCompleteTextView with product names if the list is not empty
                if (productList.isNotEmpty()) {
                    val productNames = productList.map { it.productName } // Assuming 'productName' is the field in your model
                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_dropdown_item_1line, // Use built-in layout for dropdown items
                        productNames
                    )
                    productname.setAdapter(adapter)
                } else {
                    productname.setAdapter(null) // Clear the adapter if the list is empty
                }
            }
        }

        productname.setOnClickListener {
            productname.showDropDown()
        }

        binding.cancelbtn.setOnClickListener {
            dismiss()
        }

        binding.setpricebtn.setOnClickListener {


        }
    }
}
