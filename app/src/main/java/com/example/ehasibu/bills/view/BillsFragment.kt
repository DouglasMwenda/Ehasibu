package com.example.ehasibu.bills.view

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.ehasibu.bills.model.BillsResponse
import com.example.ehasibu.bills.viewmodel.BillsProvider
import com.example.ehasibu.bills.viewmodel.BillsViewModel
import com.example.ehasibu.databinding.FragmentBillsBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG ="bills"
class Bills : Fragment() {
    private lateinit var binding: FragmentBillsBinding
    private val billsViewModel: BillsViewModel by viewModels {

        val SharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = SharedPrefs.getString(API_TOKEN, "")!!

        val repo = BillsRepo(token)
        BillsProvider(repo)

    }

    companion object {
        fun newInstance() = Bills()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBillsBinding.inflate(inflater, container, false)
        billsViewModel.bill.observe(viewLifecycleOwner) { bills ->
            if (bills != null) {
                updateBillsTable(bills)
                Log.d("BillsFragment", "Fetched bills: ${bills.bills}")
                println("fetched bills:${bills.bills}")


            } else {
                Log.d("BillsFragment", "Bills response is null")
            }

        }

        return binding.root
    }


    private fun updateBillsTable(billsResponse: BillsResponse) {
        val tableLayout = binding.billstable
        tableLayout.removeAllViews()


        for (bill in billsResponse.bills) {

            val row = TableRow(context).apply {
                layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT)
                background = resources.getDrawable(R.color.colorMaroon, null)
            }

            val id = TextView(context).apply {
                text = bill.id.toString()
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val po = TextView(context).apply {
                text = bill.poNumber
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val name = TextView(context).apply {
                text = bill.vendor.vendorName
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val payment_date = TextView(context).apply {
                text = bill.paymentDate
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val status = TextView(context).apply {
                text = bill.status
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val tax = TextView(context).apply {
                text = bill.withholdingTaxPayable.toString()
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val billed_amount = TextView(context).apply {
                text = bill.billedAmount.toString()
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val amount_payable = TextView(context).apply {
                text = bill.amountPayable.toString()
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val input_tax = TextView(context).apply {
                text = bill.inputTax.toString()
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val vendor_id = TextView(context).apply {
                text = bill.vendor.vendorId
                setTextColor(resources.getColor(R.color.black, null))
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                ).apply {
                    weight = 1f
                }
            }

            val actionSpinner = Spinner(context).apply {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT
                )
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
                                // Implement edit functionality
                            }

                            "Delete" -> {
                                // Implement delete functionality
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
            row.addView(payment_date)
            row.addView(status)
            row.addView(tax)
            row.addView(billed_amount)
            row.addView(amount_payable)
            row.addView(input_tax)
            row.addView(vendor_id)
            row.addView(actionSpinner)

            tableLayout.addView(row)
        }

        // Request layout to redraw the table
        tableLayout.requestLayout()
        tableLayout.invalidate()
    }
}
