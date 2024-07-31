package com.example.ehasibu.customerinformation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import com.example.ehasibu.customerinformation.viewmodel.AddCustomerViewmodel
import com.example.ehasibu.databinding.FragmentCustomerDialogBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

const val TAG = "Add Customer"

class CustomerDialog : DialogFragment() {
    private lateinit var binding: FragmentCustomerDialogBinding
    private lateinit var  customerType : AutoCompleteTextView


    private val viewModel: AddCustomerViewmodel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")
            ?: throw IllegalStateException("API Token is missing")
        AddCustomerViewmodel.AddCustomerProvider(CustomersRepo(token))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerDialogBinding.inflate(inflater,container,false)
        customerType = binding.customerType
        val customerTypes = arrayOf("Individual", "Business")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, customerTypes)
        customerType.setAdapter(adapter)

        viewModel.isCustomerAdded.observe(viewLifecycleOwner){ isSuccess ->
            if (isSuccess == true) {
                Toast.makeText(context, "Customer added successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "Failed to add Customer", Toast.LENGTH_SHORT).show()
            }
        }

       binding.savecustomerbutton.setOnClickListener{
           val customer = CustomerRequest(
               customerType = binding.customerType.text.toString(),
               customerFirstName = binding.firstname.text.toString(),
               customerLastName = binding.lastname.text.toString(),
               phoneNumber = binding.phonenumbner.text.toString(),
               emailAddress = binding.emailadress.text.toString(),
               companyName = binding.companyname.text.toString(),
               address = binding.adress.text.toString()

           )
           Log.d("CustomerDialog", "Customer data: $customer")
           viewModel.createCustomer(customer)

        }
        binding.cancelcustomerbutton.setOnClickListener{
            dismiss()

        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        fun newInstance(): CustomerDialog {
            return CustomerDialog()
        }
    }

}