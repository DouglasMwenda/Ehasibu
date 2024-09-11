package com.example.ehasibu.customerinformation.view

import android.content.ContentValues
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
import com.example.ehasibu.customerinformation.data.CustomerResponse
import com.example.ehasibu.customerinformation.data.UpdateCustomerRequest
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import com.example.ehasibu.customerinformation.viewmodel.CustomerProvider
import com.example.ehasibu.customerinformation.viewmodel.CustomersViewModel
import com.example.ehasibu.databinding.FragmentCustomersBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
const val TAG ="Customers"

class Customers : Fragment() {
    private lateinit var binding: FragmentCustomersBinding

    private val customerViewModel: CustomersViewModel by viewModels{
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = CustomersRepo(token)
        CustomerProvider(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomersBinding.inflate(inflater,container,false)


        binding.addcustomerbutton.setOnClickListener {
            val dialog = CustomerDialog()
            dialog.show(parentFragmentManager, "CustomerDialog")
        }

        customerViewModel.customers.observe(viewLifecycleOwner){ customers ->
            if (customers != null) {
                Log.d(TAG, "Received customers: $customers")
                updateCustomers(customers)
            }
            else {
                Log.d(ContentValues.TAG, "No customers  to display")
            }

        }
        return binding.root

    }
    private fun updateCustomers(customers: List<CustomerResponse>) {

        val customersTable= binding.customerstable

        while (customersTable.childCount > 1) {
          customersTable.removeViewAt(1)
        }
        for (customer in customers)  {
            val row = TableRow(context).apply { gravity= Gravity.CENTER_HORIZONTAL }
            val no = TextView(context).apply {
                text= customer.customerId.toString()
                setTextColor(resources.getColor(R.color.black, null))
            }
            val type = TextView(context).apply {
                text= customer.customerType
                gravity= Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val date = TextView(context).apply {
                text = customer.entryDate
                gravity= Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val firstName = TextView(context).apply {
                text= customer.customerFirstName
                gravity= Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val lastName= TextView(context).apply {
                text= customer.customerLastName
                gravity= Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val phoneNumber = TextView(context).apply {
                text = customer.phoneNumber
                gravity= Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val email = TextView(context).apply {
                text= customer.emailAddress
                gravity= Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val company = TextView(context).apply {
                text= customer.companyName
                gravity= Gravity.CENTER
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
                                val customerUpdate= UpdateCustomerRequest(
                                    customerId = customer.customerId,
                                    customerType = customer.customerType,
                                    customerFirstName = customer.customerFirstName,
                                    customerLastName = customer.customerLastName,
                                    phoneNumber = customer.phoneNumber,
                                    emailAddress = customer.emailAddress,
                                    companyName = customer.companyName,
                                    address = customer.address

                                )

                                val dialog = CustomerDialog.newInstance(customerUpdate)
                                dialog.show(parentFragmentManager, "edit customer")
                            }

                            "Delete" -> deleteCustomer(customer.customerId)
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
            row.addView(type)
            row.addView(firstName)
            row.addView(lastName)
            row.addView(date)
            row.addView(phoneNumber)
            row.addView(email)
            row.addView(company)
            row.addView(actionSpinner)
            customersTable.addView(row)


        }
    }
    private fun deleteCustomer(customerId: Int) {
        customerViewModel.deleteCustomer(customerId)
    }

}