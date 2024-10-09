package com.example.ehasibu.product.view

import AddProduct
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentProductBinding
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.data.ProductFetchRequest
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

private const val TAG = "product"

class Product : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var addProductButton: Button
    private lateinit var searchEditText: TextInputEditText
    private lateinit var searchIcon: ImageView

    private val productViewModel: ProductViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = ProductRepository(token)
        ProductProvider(repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)

        productViewModel.products.observe(viewLifecycleOwner) { products ->
            if (products != null) {
                updateTable(products)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchEditText = binding.searchEditText
        searchIcon = binding.searchIcon

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchQueryLowerCase = s.toString().lowercase(Locale.ROOT)
                productViewModel.filterProducts(searchQueryLowerCase)
            }
        })

        searchIcon.setOnClickListener {
            val productName = binding.searchEditText.text.toString().trim()
            if (productName.isNotEmpty()) {
                productViewModel.fetchProduct(ProductFetchRequest(productName))
            } else {
                Toast.makeText(context, "Enter Product Name", Toast.LENGTH_SHORT).show()
            }
        }

        addProductButton = binding.addProductBtn
        addProductButton.setOnClickListener {
            val dialog = AddProduct()
            dialog.show(parentFragmentManager, "addProduct")
        }

        binding.setPriceBtn.setOnClickListener {
            val dialog = Set_Price()
            dialog.show(parentFragmentManager, "Set_Price")
        }

        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.products)
    }

    private fun updateTable(products: List<ProdResponse>) {
        val tableLayout = binding.tableLayout

        while (tableLayout.childCount > 1) {
            tableLayout.removeViewAt(1)
        }

        // Add rows for each product
        for (product in products) {
            val row = TableRow(context).apply {
                gravity = Gravity.CENTER_HORIZONTAL
            }
            val no = TextView(context).apply {
                text = product.productId
                setTextColor(resources.getColor(R.color.black, null))
            }
            val name = TextView(context).apply {
                text = product.productName
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val description = TextView(context).apply {
                text = product.description
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val category = TextView(context).apply {
                text = product.category
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val quantity = TextView(context).apply {
                text = product.quantity.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val unit = TextView(context).apply {
                text = product.unit
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
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
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        val action = parent.getItemAtPosition(position) as String
                        when (action) {
                            "Edit" -> {
                                val editRequest = EditRequest(
                                    productId = product.productId,
                                    productName = product.productName,
                                    description = product.description,
                                    category = product.category,
                                    unit = product.unit

                                )
                                val dialog = AddProduct.newInstance(editRequest)
                                dialog.show(parentFragmentManager, "editProduct")
                            }

                            "Delete" -> deleteProduct(product.productId)
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

    private fun deleteProduct(productId: String) {
        productViewModel.deleteProduct(productId)
    }

}

