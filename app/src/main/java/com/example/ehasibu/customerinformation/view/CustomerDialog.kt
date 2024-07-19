package com.example.ehasibu.customerinformation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.customerinformation.viewmodel.CustomersViewModel
import com.example.ehasibu.databinding.FragmentCustomerDialogBinding

class CustomerDialog : DialogFragment() {
    private lateinit var binding: FragmentCustomerDialogBinding
    private val viewModel: CustomersViewModel by viewModels()
    private lateinit var  customerType : AutoCompleteTextView
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var emailAddress: EditText
    private lateinit var companyName: EditText
    private lateinit var address:EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomerDialogBinding.inflate(inflater,container,false)
        customerType = binding.customerType
        val customerTypes = arrayOf("Individual", "Business")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, customerTypes)
        customerType.setAdapter(adapter)

        firstName = binding.firstname
        lastName = binding.lastname
        phoneNumber = binding.phonenumbner
        emailAddress = binding.emailadress
        companyName = binding.companyname
        address = binding.adress
        saveButton = binding.savecustomerbutton
        cancelButton = binding.cancelcustomerbutton

        saveButton.setOnClickListener{
            val customerTypeText = customerType.text.toString().trim()
            val firstNameText = firstName.text.toString().trim()
            val lastNameText = lastName.text.toString().trim()
            val phoneNumberText = phoneNumber.text.toString().trim()
            val emailAddressText = emailAddress.text.toString().trim()
            val companyNameText = companyName.text.toString().trim()
            val addressText = address.text.toString().trim()

            if (customerTypeText.isEmpty()) {
                customerType.error = "Please select customer type"
            }

            if (firstNameText.isEmpty()) {
                firstName.error = "Please enter first name"
            }
            if(lastNameText.isEmpty()){
                lastName.error= "Please enter last name"

            }

        }
        cancelButton.setOnClickListener{
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