package com.example.ehasibu.productsales

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.ehasibu.MainActivity
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentAddsaledialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Addsaledialog : Fragment() {
    private lateinit var binding: FragmentAddsaledialogBinding
    private val viewModel: ProductSalesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAddsaledialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datePickerEditText: EditText = binding.datepickerEdittext
        val customerNameField: AutoCompleteTextView = binding.customerNameField
        val summaryTotal: EditText = binding.summaryTotal
        val summaryTax: EditText =binding.summaryTax
        val summaryNetTotal: EditText = binding.summaryNetTotal
        val amountPaid: EditText = binding.amountPaid
        val addproductbutton : Button = binding.addproductbutton
        val paybutton : Button = binding.paybutton
        val cancelbutton: Button =binding.cancelButton

        val customerAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.customerNameField.setAdapter(customerAdapter)

        viewModel.customers.observe(viewLifecycleOwner) { customers ->
            customerAdapter.clear()
            customerAdapter.addAll(customers)
            customerAdapter.notifyDataSetChanged()
        }

        datePickerEditText.setOnClickListener {
            showDatePickerDialog(datePickerEditText)
        }


       binding.addproductbutton.setOnClickListener {

        }
        val activity: MainActivity by lazy { requireActivity() as MainActivity }
       binding.paybutton.setOnClickListener {
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
        }

        binding.cancelButton.setOnClickListener {
        }
        summaryTotal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newTotal = s.toString().toDoubleOrNull() ?: 0.0

            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })
        summaryTax.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })
        summaryNetTotal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })
        amountPaid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })


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

    fun show(childFragmentManager: FragmentManager, s: String) {

    }


}



