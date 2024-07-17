package com.example.ehasibu.productsales

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
import com.example.ehasibu.MainActivity
import com.example.ehasibu.R
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

    // private val viewModel: ProductSalesViewModel by viewModels()

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

        val customerAdapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.customerNameField.setAdapter(customerAdapter)

        /*viewModel.customers.observe(viewLifecycleOwner) { customers ->
            customerAdapter.clear()
            customerAdapter.addAll(customers)
            customerAdapter.notifyDataSetChanged()
        }*/

        datePickerEditText.setOnClickListener {
            showDatePickerDialog(datePickerEditText)
        }


        binding.addproductbutton.setOnClickListener {

        }
        val activity: MainActivity by lazy { requireActivity() as MainActivity }
        /* binding.paybutton.setOnClickListener {
             val customerName = binding.customerNameField.text.toString()
             val total = viewModel.summaryTotal.value?.toDoubleOrNull() ?: 0.0
             val tax = viewModel.summaryTax.value?.toDoubleOrNull() ?: 0.0
             val netTotal = viewModel.summaryNetTotal.value?.toDoubleOrNull() ?: 0.0
             val paidAmount = viewModel.amountPaid.value?.toDoubleOrNull() ?: 0.0


             val paymentFragment = PaymentFragment.newInstance(customerName, total, tax, netTotal, paidAmount)

             activity.supportFragmentManager.beginTransaction()
                 .replace(R.id.addSaleDialog, paymentFragment)
                 .addToBackStack(null)
                 .commit()
          }*/

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



