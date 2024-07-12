package com.example.ehasibu.purchaseorder.view

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
import com.example.ehasibu.databinding.FragmentPurchaseOrderBinding
import com.example.ehasibu.purchaseorder.data.OrdersEntity
import com.example.ehasibu.purchaseorder.repo.OrderRepo
import com.example.ehasibu.purchaseorder.viewmodel.OrdersProvider
import com.example.ehasibu.purchaseorder.viewmodel.PurchaseOrderViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG = "purchases"

class Purchase_Order : Fragment() {
    private lateinit var binding: FragmentPurchaseOrderBinding

    private val orderViewModel: PurchaseOrderViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = OrderRepo(token)
        OrdersProvider(repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseOrderBinding.inflate(inflater, container, false)
        orderViewModel.orders.observe(viewLifecycleOwner) { orders ->
            if (orders != null) {
                updateOrders(orders)
            } else {
                Log.d(TAG, "No orders to display")
            }
        }

        return binding.root
    }

    private fun updateOrders(orders: List<OrdersEntity>) {
        val tableLayout = binding.OrdersTable

        while (tableLayout.childCount > 1) {
            tableLayout.removeViewAt(1)
        }

        for (order in orders) {
            val row = TableRow(context).apply { gravity = Gravity.CENTER_HORIZONTAL }
            val no = TextView(context).apply {
                text = order.id.toString()
                setTextColor(resources.getColor(R.color.black, null))
            }
            val name = TextView(context).apply {
                text = order.vendor.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val purchaseDate = TextView(context).apply {
                text = order.purchaseDate
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val deliveryDate = TextView(context).apply {
                text = order.deliveryDate
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val status = TextView(context).apply {
                text = order.purchaseStatus
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
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
            row.addView(purchaseDate)
            row.addView(deliveryDate)
            row.addView(status)
            row.addView(actionSpinner)
            tableLayout.addView(row)
        }
    }
}
