package com.example.ehasibu.bills.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.bills.Repository.BillsRepo
import com.example.ehasibu.bills.model.BillRequest
import com.example.ehasibu.bills.viewmodel.NewBillProvider
import com.example.ehasibu.bills.viewmodel.NewBillViewModel
import com.example.ehasibu.databinding.FragmentNewBillBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import java.util.Calendar

private const val TAG ="newbill"
class NewBillFragment : DialogFragment() {
    private lateinit var binding: FragmentNewBillBinding

    private val newBillViewModel: NewBillViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = BillsRepo(token)

        NewBillProvider(repo)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewBillBinding.inflate(inflater, container, false)
        newBillViewModel.isBillAdded.observe(viewLifecycleOwner) { isSuccess ->
            Log.d(TAG, "Bill saved successfully: $isSuccess")
            if (isSuccess == true) {
                Toast.makeText(context, "Bill saved successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "Failed to save bill", Toast.LENGTH_SHORT).show()
            }
        }
        binding.paymentdate.setOnClickListener {
            showDatePicker()
        }
        binding.cancelbillbutton.setOnClickListener {
            dismiss()
        }
        binding.savebillbutton.setOnClickListener {
            if (validInput()) {
                val bill = BillRequest(
                    poNumber = binding.POnumber.text.toString(),
                    vendorId = binding.vendorId.text.toString (),
                    vendor = binding.vendorname.text.toString(),
                    quantity = binding.quantity.text.toString().toInt(),
                    buyingPrice = binding.buyingprice.text.toString().toInt(),
                    paymentDate = binding.paymentdate.text.toString(),
                    amountForVAT = 0
                )
                newBillViewModel.addBill(bill)
            }

        }
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        val dialog = dialog ?: return
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window?.setLayout(width, height)
    }

    private fun validInput(): Boolean {
        return when {
            binding.vendorId.text.isNullOrBlank() -> {
                Toast.makeText(context, "Vendor ID is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.vendorname.text.isNullOrBlank() -> {
                Toast.makeText(context, "Vendor name is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.POnumber.text.isNullOrBlank() -> {
                Toast.makeText(context, "PO number is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.quantity.text.isNullOrBlank() -> {
                Toast.makeText(context, "Quantity is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.buyingprice.text.isNullOrBlank() -> {
                Toast.makeText(context, "Buying price is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.paymentdate.text.isNullOrBlank() -> {
                Toast.makeText(context, "Payment date is required", Toast.LENGTH_SHORT).show()
                false
            }

            else -> true

        }

    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(),
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.paymentdate.setText(formattedDate)
            }, year, month, day
        )

        datePickerDialog.show()
    }
}