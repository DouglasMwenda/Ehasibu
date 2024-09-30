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
import com.example.ehasibu.customerinformation.data.UpdateCustomerRequest
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import com.example.ehasibu.customerinformation.viewmodel.AddCustomerViewmodel
import com.example.ehasibu.databinding.FragmentCustomerDialogBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF


class CustomerDialog : DialogFragment() {
    private lateinit var binding: FragmentCustomerDialogBinding
    private lateinit var  customerType : AutoCompleteTextView
    private var editRequest: UpdateCustomerRequest? = null


    private val viewModel: AddCustomerViewmodel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo= CustomersRepo(token)
        AddCustomerViewmodel.AddCustomerProvider(repo)
    }
    companion object{
        private const val ARG_EDIT_REQUEST = "edit_request"

        fun newInstance (editRequest: UpdateCustomerRequest? = null) : CustomerDialog {
            val fragment = CustomerDialog()
            val args = Bundle().apply {
                putParcelable(ARG_EDIT_REQUEST, editRequest)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            editRequest = it.getParcelable(ARG_EDIT_REQUEST)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerDialogBinding.inflate(inflater,container,false)
        customerType = binding.customerType
        val customerTypes = arrayOf("Individual", "com.example.ehasibu.expenses.model.com.example.ehasibu.expenses.model.Business")
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
           if (validateInput()) {
               if (editRequest == null) {

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
               else {
                   val customer = UpdateCustomerRequest(
                       customerId = editRequest!!.customerId,
                       customerType = binding.customerType.toString(),
                      customerFirstName = binding.firstname.toString(),
                       customerLastName = binding.lastname.toString(),
                       phoneNumber = binding.phonenumbner.toString(),
                       emailAddress = binding.emailadress.toString(),
                       companyName = binding.companyname.toString(),
                       address = binding.adress.toString()
                   )
                   viewModel.updateCustomer(customer)
               }
               }
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

    private fun validateInput(): Boolean {
        return when {
            binding.customerType.text.isNullOrBlank() -> {
                Toast.makeText(context, "Customer Type name is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.firstname.text.isNullOrBlank() -> {
                Toast.makeText(context, "firstname is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.lastname.text.isNullOrBlank() -> {
                Toast.makeText(context, "Lastname is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.phonenumbner.text.isNullOrBlank() -> {
                Toast.makeText(context, "Phone number is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.emailadress.text.isNullOrBlank() -> {
                Toast.makeText(context, "Email  is required", Toast.LENGTH_SHORT).show()
                false
            }
            binding.companyname.text.isNullOrBlank() -> {
                Toast.makeText(context, "Company name is required", Toast.LENGTH_SHORT).show()
                false
            }
            binding.adress.text.isNullOrBlank() -> {
                Toast.makeText(context, "Address is required", Toast.LENGTH_SHORT).show()
                false
            }

            else -> true
        }
    }


}