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
import android.widget.Toast
import com.example.ehasibu.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Addsaledialog : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addsaledialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datePickerEditText: EditText = view.findViewById(R.id.datepicker_edittext)
        val customerNameField: AutoCompleteTextView = view.findViewById(R.id.customerNameField)
        val summaryTotal: EditText = view.findViewById(R.id.summaryTotal)
        val summaryTax: EditText = view.findViewById(R.id.summaryTax)
        val summaryNetTotal: EditText = view.findViewById(R.id.summaryNetTotal)
        val amountPaid: EditText = view.findViewById(R.id.amountPaid)
        val addproductbutton : Button = view.findViewById(R.id.addproductbutton)
        val paybutton : Button = view.findViewById(R.id.paybutton)
        val cancel_button: Button = view.findViewById(R.id.cancel_button)


        // Sample customer list
        val customers = listOf("John Doe", "Jane Smith", "Alice Johnson", "Robert Brown")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, customers)
        customerNameField.setAdapter(adapter)

        datePickerEditText.setOnClickListener {
            showDatePickerDialog(datePickerEditText)
        }


        customerNameField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Handle before text changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Handle on text changed
            }
        })

        addproductbutton.setOnClickListener {

            Toast.makeText(requireContext(), "Add Product Button Clicked", Toast.LENGTH_SHORT).show()
        }

        paybutton.setOnClickListener {
            Toast.makeText(requireContext(), "Proceed to Pay Button Clicked", Toast.LENGTH_SHORT).show()
        }

        cancel_button.setOnClickListener {
            Toast.makeText(requireContext(), "Cancel Button Clicked", Toast.LENGTH_SHORT).show()
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



