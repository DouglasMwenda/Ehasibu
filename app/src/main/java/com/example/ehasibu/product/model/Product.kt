package com.example.ehasibu.product.model

import AddProduct
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentProductBinding
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.data.ProductFetchRequest
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import com.google.android.material.textfield.TextInputEditText

private const val TAG = "product"

class Product : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var AddProductbutton: Button
    private lateinit var searchEditText: TextInputEditText
    private lateinit var searchIcon: ImageView
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


        searchEditText = binding.searchEditText
        searchIcon = binding.searchIcon

        binding.searchIcon.setOnClickListener {
            val productName = binding.searchEditText.text.toString().trim()
            if (productName.isNotEmpty()) {
                productViewModel.fetchProduct(ProductFetchRequest(productName))
            }else{
                Toast.makeText(context, "Enter Product Name", Toast.LENGTH_SHORT).show()
            }
        }



        AddProductbutton = binding.addProductBtn
        AddProductbutton.setOnClickListener {
            val dialog = AddProduct()
            dialog.show(parentFragmentManager, "addProduct")

            binding.setPriceBtn.setOnClickListener {
                // Implement the logic for setting the price
            }

        }
        return binding.root
    }

        private fun updateTable(products: List<ProdResponse>) {
            val tableLayout = binding.tableLayout

            while (tableLayout.childCount > 1) {
                tableLayout.removeViewAt(1)
            }

            // Add rows for each product
            for (product in products) {
                val row = TableRow(context).apply { gravity = Gravity.CENTER_HORIZONTAL }
                val no =
                    TextView(context).apply { text = product.productId
                     //   gravity = Gravity.NO_GRAVITY
                        setTextColor(resources.getColor(R.color.black, null)) }
                val name =
                    TextView(context).apply { text = product.productName;
                        gravity = Gravity.CENTER
                        setTextColor(resources.getColor(R.color.black, null)) }
                val description =
                    TextView(context).apply { text = product.description
                        gravity = Gravity.CENTER
                        setTextColor(resources.getColor(R.color.black, null)) }
                val category =
                    TextView(context).apply { text = product.category
                       gravity = Gravity.CENTER
                        setTextColor(resources.getColor(R.color.black, null)) }
                val quantity = TextView(context).apply {
                    text = product.quantity.toString()
                    gravity = Gravity.CENTER
                    setTextColor(resources.getColor(R.color.black, null)) }
                val unit = TextView(context).apply { text = product.unit
                    gravity = Gravity.CENTER
                    setTextColor(resources.getColor(R.color.black, null)) }
                val buyingPrice = TextView(context).apply {
                    text = product.buyingPrice.toString()
                    gravity = Gravity.CENTER
                    setTextColor(resources.getColor(R.color.black, null))
                }
                val sellingPrice = TextView(context).apply {
                    text = product.sellingPrice.toString()
                    gravity = Gravity.CENTER
                    setTextColor(resources.getColor(R.color.black, null))
                }

                val actionSpinner = Spinner(context).apply {
                    gravity = Gravity.START
                    adapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_item,
                        listOf("Action", "Edit", "Delete")
                    ).also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }

                    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val action = parent.getItemAtPosition(position) as String
                            when (action) {
                                "Edit" -> editProduct(product)
                                "Delete" -> deleteProduct(product)
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            // Do nothing
                        }

                    }
                    setBackgroundResource(android.R.drawable.btn_default)
                    setPadding(0, 0, 0, 0)

                }

                // Add all views to the row
                row.addView(no)
                row.addView(name)
                row.addView(description)
                row.addView(category)
                row.addView(quantity)
                row.addView(unit)
                row.addView(buyingPrice)
                row.addView(sellingPrice)

                row.addView(actionSpinner)

                tableLayout.addView(row)
            }
        }

    private fun editProduct(product: ProdResponse) {
        // edit product logic here
       // Toast.makeText(context, "Edit product: ${product.productName}", Toast.LENGTH_SHORT).show()
    }

    private fun deleteProduct(product: ProdResponse) {
        // delete product logic here
        //Toast.makeText(context, "Delete product: ${product.productName}", Toast.LENGTH_SHORT).show()
    }
}

