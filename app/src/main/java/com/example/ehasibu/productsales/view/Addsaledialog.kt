package com.example.ehasibu.productsales.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.ehasibu.R
import com.example.ehasibu.customerinformation.viewmodel.CustomersViewModel
import com.example.ehasibu.databinding.FragmentAddsaledialogBinding
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddsaledialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireParentFragment()).get(CustomersViewModel::class.java)

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

        viewModel.customers.observe(viewLifecycleOwner) { customerResponses ->
            customerResponses?.let { customers ->
                val customerNames = customers.map { it.customerFirstName }

                val customerAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    customerNames
                )
                customerNameField.setAdapter(customerAdapter)

                customerNameField.setOnClickListener {
                    customerNameField.showDropDown()
                }
            }
        }


        binding.addproductbutton.setOnClickListener {

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

}



