package com.example.ehasibu.bills.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.bills.Repository.BillsRepo
import com.example.ehasibu.bills.model.Bill
import com.example.ehasibu.bills.viewmodel.BillsProvider
import com.example.ehasibu.bills.viewmodel.BillsViewModel
import com.example.ehasibu.databinding.FragmentBillsBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG = "bills"

class Bills : Fragment() {
    private lateinit var binding: FragmentBillsBinding

    private val billsViewModel: BillsViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = BillsRepo(token)
        BillsProvider(repo)

    }

    /* companion object {
         fun newInstance() = Bills()
     }

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         // TODO: Use the ViewModel
     }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBillsBinding.inflate(inflater, container, false)

        billsViewModel.bills.observe(viewLifecycleOwner) { bills ->
            Log.d(TAG, "Fetched bills: $bills")

            if (bills != null)
                requireActivity().runOnUiThread() {
                    updateBillsTable(bills)

                }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addbillbutton.setOnClickListener {
            val dialog = NewBillFragment()
            dialog.show(parentFragmentManager, "newBill")

        }


    }

    private fun updateBillsTable(bills: List<Bill>) {
        val tableLayout = binding.billstable


        tableLayout.removeViewsInLayout(1, tableLayout.childCount - 1)

        for (bill in bills) {

            val row = TableRow(context).apply {
                gravity = Gravity.CENTER_HORIZONTAL
            }

            val id = TextView(context).apply {
                text = bill.id.toString()
                setTextColor(resources.getColor(R.color.black, null))
            }

            val po = TextView(context).apply {
                text = bill.poNumber
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }

            val name = TextView(context).apply {
                text = bill.vendor.vendorName
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }

            val vendorId = TextView(context).apply {
                text = bill.vendor.vendorId
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }
            val amountForVAT = TextView(context).apply {
                text = bill.amountForVAT.toString()
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }
            val inputTax = TextView(context).apply {
                text = bill.inputTax.toString()
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }
            val billedAmount = TextView(context).apply {
                text = bill.billedAmount.toString()
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }
            val withholdingTax = TextView(context).apply {
                text = bill.withholdingTaxPayable.toString()
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }

            val amountPayable = TextView(context).apply {
                text = bill.amountPayable.toString()
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }

            val paymentDate = TextView(context).apply {
                text = bill.paymentDate
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
            }

            val status = TextView(context).apply {
                text = bill.status
                setTextColor(resources.getColor(R.color.black, null))
                gravity = Gravity.CENTER
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
                            }

                            "Approve" -> {

                            }

                            "Reject" -> {

                            }

                            "Pay" -> {

                            }

                            "Delete" -> {
                            }

                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // Do nothing
                    }
                }
                setBackgroundResource(android.R.drawable.btn_default)
                setPadding(0, 0, 0, 0)
            }

            row.addView(id)
            row.addView(po)
            row.addView(name)
            row.addView(vendorId)
            row.addView(amountForVAT)
            row.addView(inputTax)
            row.addView(billedAmount)
            row.addView(withholdingTax)
            row.addView(amountPayable)
            row.addView(paymentDate)
            row.addView(status)
            row.addView(actionSpinner)

            tableLayout.addView(row)
        }
    }

}

