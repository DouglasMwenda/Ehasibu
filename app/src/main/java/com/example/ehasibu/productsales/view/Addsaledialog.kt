package com.example.ehasibu.productsales.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.ehasibu.R
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import com.example.ehasibu.customerinformation.viewmodel.CustomerProvider
import com.example.ehasibu.customerinformation.viewmodel.CustomersViewModel
import com.example.ehasibu.databinding.FragmentAddsaledialogBinding
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
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


    private val
            customerViewModel: CustomersViewModel by viewModels{
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
                    val customerNames =
                        customers.map { "${it.customerFirstName} ${it.customerLastName}" }

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
            productViewModel.products.observe(viewLifecycleOwner) { products ->
                if (products != null) {
                    updateTable(products)
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
                selectedDate.set(selectedYear, selectedMonth, 1)
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                editText.setText(format.format(selectedDate.time))
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun updateTable(products: List<ProdResponse>) {
        val tableLayout = binding.producttable

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


            // Add all views to the row
            row.addView(no)
            row.addView(name)
            row.addView(description)
            row.addView(category)
            row.addView(quantity)
            row.addView(unit)
            row.addView(buyingPrice)
            row.addView(sellingPrice)

            tableLayout.addView(row)
        }

    }

}



