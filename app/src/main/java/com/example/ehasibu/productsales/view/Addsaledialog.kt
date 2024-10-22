package com.example.ehasibu.productsales.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import com.example.ehasibu.customerinformation.viewmodel.CustomerProvider
import com.example.ehasibu.customerinformation.viewmodel.CustomersViewModel
import com.example.ehasibu.databinding.FragmentAddsaledialogBinding
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
import com.example.ehasibu.productsales.repo.SalesRepository
import com.example.ehasibu.productsales.viewModel.ProductSalesViewModel
import com.example.ehasibu.productsales.viewModel.SalesProvider
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Addsaledialog : DialogFragment() {
    private lateinit var binding: FragmentAddsaledialogBinding
    private lateinit var datePickerEditText: TextInputEditText
    private lateinit var customerNameField: AutoCompleteTextView
    private lateinit var summaryTotal: TextInputEditText
    private lateinit var summaryTax: TextInputEditText
    private lateinit var summaryNetTotal: TextInputEditText
    private lateinit var amountPaid: TextInputEditText
    private lateinit var modeOfPayment: AutoCompleteTextView
    private lateinit var addproductButton: Button
    private lateinit var paybutton: Button
    private lateinit var cancelbutton: Button


    private val customerViewModel: CustomersViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = CustomersRepo(token)
        CustomerProvider(repo)
    }

    private val productViewModel: ProductViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = ProductRepository(token)
        ProductProvider(repo)
    }

    private val salesViewmodel: ProductSalesViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = SalesRepository(token)
        SalesProvider(repo)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddsaledialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datePickerEditText = binding.datepicker
        customerNameField = binding.customerNameField
        summaryTotal = binding.summaryTotal
        summaryTax = binding.summaryTax
        summaryNetTotal = binding.summaryNetTotal
        amountPaid = binding.amountPaid
        modeOfPayment = binding.modeofpayment

        datePickerEditText.setOnClickListener {
            showDatePickerDialog(datePickerEditText)
        }

        val paymentModesArray: Array<String> = resources.getStringArray(R.array.payment_modes)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            paymentModesArray
        )


        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)


        modeOfPayment.setAdapter(adapter)


        modeOfPayment.setOnClickListener {
            modeOfPayment.showDropDown()
        }
        addproductButton = binding.addproductbutton
        paybutton = binding.paybutton
        cancelbutton = binding.cancelButton

        customerViewModel.customers.observe(viewLifecycleOwner) { customerResponses ->
            customerResponses?.let { customers ->
                if (customers.isNotEmpty()) {
                    val customerNames = customers.map { "${it.customerFirstName} ${it.customerLastName}" }

                    val customerAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        customerNames
                    )
                    customerNameField.setAdapter(customerAdapter)
                } else {
                    customerNameField.setAdapter(null)
                }
            }
        }

        customerNameField.setOnClickListener {
            customerNameField.showDropDown()
        }

        binding.addproductbutton.setOnClickListener {
            val tableLayout = binding.productTable

            val row = TableRow(context).apply {
                gravity = Gravity.CENTER_HORIZONTAL
            }

            val productAutoCompleteTextView = AutoCompleteTextView(context).apply {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                hint = "Select Product"
            }

            val descriptionTextView = TextView(context)
            val categoryTextView = TextView(context)
            val unitTextView = TextView(context)
            val buyingPriceTextView = TextView(context)
            val sellingPriceTextView = TextView(context)

            row.addView(productAutoCompleteTextView)
            row.addView(descriptionTextView)
            row.addView(categoryTextView)
            row.addView(unitTextView)
            row.addView(buyingPriceTextView)
            row.addView(sellingPriceTextView)


            tableLayout.addView(row)


            productViewModel.products.observe(viewLifecycleOwner) { productList ->
                productList?.let { nonNullProductList ->
                    val productNames = nonNullProductList.map { it.productName }


                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        productNames
                    )
                    productAutoCompleteTextView.setAdapter(adapter)


                    productAutoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
                        val selectedProductName = productNames[position]
                        val selectedProduct = nonNullProductList.find { it.productName == selectedProductName }

                        selectedProduct?.let {
                            descriptionTextView.text = it.description
                            categoryTextView.text = it.category
                            unitTextView.text = it.unit
                            buyingPriceTextView.text = it.buyingPrice.toString()
                            sellingPriceTextView.text = it.sellingPrice.toString()
                        }
                    }
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        paybutton.setOnClickListener {


            dismiss()
            val dialog = PaymentFragment()
            dialog.show(parentFragmentManager, "PaymentFragment")
        }

    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, _ ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth + 1, 1)
                val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                editText.setText(format.format(selectedDate.time))
            },
           day, month, year

        )
        datePickerDialog.show()
    }


}




