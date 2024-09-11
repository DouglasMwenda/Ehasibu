package com.example.ehasibu.vendors.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentNewVendorBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import com.example.ehasibu.vendors.model.AddRequest
import com.example.ehasibu.vendors.model.EditVRequest
import com.example.ehasibu.vendors.model.VendorRepo
import com.example.ehasibu.vendors.viewmodel.NewVendorViewModel


class NewVendor : DialogFragment() {
    private lateinit var binding: FragmentNewVendorBinding
    private lateinit var vendorType: AutoCompleteTextView
    private lateinit var currency: AutoCompleteTextView
    private var editRequest: EditVRequest? = null
    private val viewModel: NewVendorViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!

        NewVendorViewModel.AddVProvider(VendorRepo(token))

    }
    companion object {
        private const val ARG_EDIT_VENDOR = "edit_vendor"
        fun newInstance(editRequest: EditVRequest? = null): NewVendor {
            val fragment = NewVendor()
            val args = Bundle().apply {
                putParcelable(ARG_EDIT_VENDOR,editRequest)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            editRequest = it.getParcelable(ARG_EDIT_VENDOR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewVendorBinding.inflate(inflater, container, false)
        viewModel.isVendorAdded.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess == true) {
                Toast.makeText(context, "Vendor saved successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "Failed to save vendor", Toast.LENGTH_SHORT).show()
            }
        }
        vendorType = binding.vendortype
        val vendorTypes = arrayOf("Products", "Services")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, vendorTypes)
        vendorType.setAdapter(adapter)

        currency = binding.currency
        val currencies = arrayOf("KES","USD","EUR","TZS","UGX","Rwandan franc","CFA franc","Kuwait dinar")
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, currencies)
        currency.setAdapter(adapter2)


        binding.savevendorbutton.setOnClickListener {
            if (validateInput()) {
                if (editRequest == null) {
                    val vendor = AddRequest(
                        vendorName = binding.vendorname.text.toString(),
                        vendorPin = binding.vendorpin.text.toString(),
                        vendorType = binding.vendortype.text.toString(),
                        address = binding.address.text.toString(),
                        phone = binding.phonenumber.text.toString(),
                        email = binding.email.text.toString(),
                        otherDetails = binding.currency.text.toString(),
                        displayName = binding.displayname.text.toString()
                    )
                    viewModel.addVendor(vendor)

                } else {
                    val vendor = EditVRequest(
                        vendorName = binding.vendorname.text.toString(),
                        vendorPin = binding.vendorpin.text.toString(),
                        vendorType = binding.vendortype.toString(),
                        address = binding.address.text.toString(),
                        phone = binding.phonenumber.text.toString(),
                        email = binding.email.text.toString(),
                        otherDetails = binding.currency.text.toString(),
                        displayName = binding.displayname.text.toString()
                    )
                    viewModel.updateVendor(vendor)
                }

            }
        }
        binding.cancelVendorBtn.setOnClickListener {
            dismiss()
        }
        editRequest?.let {
            binding.vendorname.setText(it.vendorName)
            binding.vendorpin.setText(it.vendorPin)
            binding.vendortype.setText(it.vendorType)
            binding.address.setText(it.address)
            binding.phonenumber.setText(it.phone)
            binding.email.setText(it.email)
            binding.currency.setText(it.otherDetails)
            binding.displayname.setText(it.displayName)
        }



return binding.root
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun validateInput(): Boolean {
        return when {
            binding.vendorname.text.isNullOrBlank() -> {
                Toast.makeText(context, "Vendor name is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.vendorpin.text.isNullOrBlank() -> {
                Toast.makeText(context, "Vendor pin is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.vendortype.text.isNullOrBlank() -> {
                Toast.makeText(context, "Vendor type is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.address.text.isNullOrBlank() -> {
                Toast.makeText(context, "Address is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.phonenumber.text.isNullOrBlank() -> {
                Toast.makeText(context, "Phone number is required", Toast.LENGTH_SHORT).show()
                false
            }

            binding.email.text.isNullOrBlank() -> {
                Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show()
                false
            }

            else -> true

        }

    }



}