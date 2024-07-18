package com.example.ehasibu.productsales.view

import android.content.ContentValues.TAG
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
import com.example.ehasibu.databinding.FragmentProductsalesBinding
import com.example.ehasibu.productsales.data.SalesEntity
import com.example.ehasibu.productsales.repo.SalesRepository
import com.example.ehasibu.productsales.viewModel.ProductSalesViewModel
import com.example.ehasibu.productsales.viewModel.SalesProvider
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

class ProductSales : Fragment() {

    private lateinit var binding: FragmentProductsalesBinding

    private val productSalesViewModel: ProductSalesViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = SalesRepository(token)
        SalesProvider(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsalesBinding.inflate(inflater, container, false)

        binding.addsalebutton.setOnClickListener {
            val dialog = Addsaledialog()
            dialog.show(parentFragmentManager, "Addsaledialog")
        }

        productSalesViewModel.sales.observe(viewLifecycleOwner) { sales ->
            if (sales != null) {
                updateSalesTable(sales)
            } else {
                Log.d(TAG, "No sales to display")
            }
        }

        return binding.root
    }

    private fun updateSalesTable(sales: List<SalesEntity>) {

        val salesTableLayout = binding.salesTableLayout

        while (salesTableLayout.childCount > 1) {
            salesTableLayout.removeViewAt(1)
        }
        for (sale in sales) {

            val row = TableRow(context).apply { gravity = Gravity.CENTER_HORIZONTAL }
            val no = TextView(context).apply {
                text = sale.saleId
                setTextColor(resources.getColor(R.color.black, null))
            }
            val name = TextView(context).apply {
                text = sale.customerName
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val date = TextView(context).apply {
                text = sale.salesDate
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val netAmount = TextView(context).apply {
                text = sale.amountForVat.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val taxAmount = TextView(context).apply {
                text = sale.totalTax.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val totalAmount = TextView(context).apply {
                text = sale.totalAmount.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val payment = TextView(context).apply {
                text = sale.modeOfPayment
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val status = TextView(context).apply {
                text = sale.status
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val actionSpinner = Spinner(context).apply {
                gravity = Gravity.START
                adapter = ArrayAdapter(
                    context,
                    android.R.layout.simple_spinner_item,
                    listOf("Action", "Approve", "Delete")
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
                                // Handle edit action
                            }

                            "Delete" -> {
                                // Handle delete action
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

            row.addView(no)
            row.addView(name)
            row.addView(date)
            row.addView(netAmount)
            row.addView(taxAmount)
            row.addView(totalAmount)
            row.addView(payment)
            row.addView(status)
            row.addView(actionSpinner)
            salesTableLayout.addView(row)

        }

    }
}

