package com.example.ehasibu.product.model

import Add_Product
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentProductBinding
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG = "product"

class Product : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var AddProductbutton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)

        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!

        val repo = ProductRepository(token)

        val productViewModel: ProductViewModel by viewModels {
            ProductProvider(repo)
        }

        productViewModel.products.observe(viewLifecycleOwner) { products ->
            updateTable(products)
        }

        AddProductbutton= binding.addProductBtn
        AddProductbutton.setOnClickListener {
            val dialog= Add_Product()
            dialog.show(parentFragmentManager,"addProduct")
        }

        binding.setPriceBtn.setOnClickListener {
            // Implement the logic for setting the price
        }

        return binding.root
    }

    private fun updateTable(products: List<ProdResponse>) {
        val tableLayout = binding.tableLayout

        // Clear existing rows (except for the header row)
        while (tableLayout.childCount > 1) {
            tableLayout.removeViewAt(1)
        }

        // Add rows for each product
        for (product in products) {
            val row = TableRow(context).apply { gravity= Gravity.CENTER_HORIZONTAL }
            val no =TextView(context).apply { text= product.productId; gravity = Gravity.CENTER }
            val name = TextView(context).apply { text = product.productName; gravity = Gravity.CENTER }
            val description = TextView(context).apply { text = product.description; gravity = Gravity.CENTER}
            val category = TextView(context).apply { text = product.category; gravity = Gravity.CENTER }
            val quantity = TextView(context).apply { text = product.quantity.toString(); gravity = Gravity.CENTER }
            val unit = TextView(context).apply { text = product.unit; gravity = Gravity.CENTER }
            val buyingPrice = TextView(context).apply { text = product.buyingPrice.toString(); gravity = Gravity.CENTER }
            val sellingPrice = TextView(context).apply { text = product.sellingPrice.toString(); gravity = Gravity.CENTER }
            // Add other product details as necessary

            row.addView(no)
            row.addView(name)
            row.addView(description)
            row.addView(category)
            row.addView(quantity)
            row.addView(unit)
            row.addView(buyingPrice)
            row.addView(sellingPrice)
            // Add other views to the row

            tableLayout.addView(row)
        }
    }
}
