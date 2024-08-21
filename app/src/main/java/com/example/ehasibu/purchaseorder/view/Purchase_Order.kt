package com.example.ehasibu.purchaseorder.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentPurchaseOrderBinding
import com.example.ehasibu.purchaseorder.data.OrderEntity
import com.example.ehasibu.purchaseorder.repo.OrderRepo
import com.example.ehasibu.purchaseorder.viewmodel.OrdersProvider
import com.example.ehasibu.purchaseorder.viewmodel.PurchaseOrderViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val TAG = "purchases"

class PurchaseOrder : Fragment() {

    private lateinit var binding: FragmentPurchaseOrderBinding
    private lateinit var datePickerEditText: EditText
    private lateinit var datePickerEditText2: EditText
    private lateinit var addButton: ImageButton

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

        orderViewModel.order.observe(viewLifecycleOwner) { result ->
            result?.let {
                Log.i(TAG, "Order approved: $it")
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datePickerEditText2 = binding.datepicker2
        datePickerEditText = binding.datepicker1

        datePickerEditText.setOnClickListener {
            showDatePickerDialog(datePickerEditText)
        }
        datePickerEditText2.setOnClickListener {
            showDatePickerDialog(datePickerEditText2)
        }

        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.supportActionBar?.title =getString(R.string.purchase_orders)




        addButton = binding.addPurchaseBtn
        addButton.setOnClickListener {
            val dialog = AddPOFragment.newInstance()
            dialog.show(parentFragmentManager, "addPO")
        }



    }

    private fun updateOrders(orders: List<OrderEntity>) {
        val tableLayout = binding.OrdersTable

        while (tableLayout.childCount > 1) {
            tableLayout.removeViewAt(1)
        }

        for (order in orders) {
            val row = TableRow(context).apply { gravity = Gravity.CENTER_HORIZONTAL }
            val no = TextView(context).apply {
                text = order.id
                setTextColor(resources.getColor(R.color.black, null))
            }
            val name = TextView(context).apply {
                text = order.vendor.vendorName.toString()
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
                    listOf("Action", "Approve", "Reject", "Deliver", "Delete")
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
                            "Approve" -> orderViewModel.approveOrder(order.id)

                            "Reject" -> {
                                // Handle delete action
                            }

                            "Deliver" -> {
                                // Handle delete action
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


   /* private fun approveOrder(id: String) {
        orderViewModel.approveOrder(id)
    }*/
}