package com.example.ehasibu.vendors.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentNewVendorBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import com.example.ehasibu.vendors.moddel.AddRequest
import com.example.ehasibu.vendors.moddel.EditVRequest
import com.example.ehasibu.vendors.moddel.VendorRepo
import com.example.ehasibu.vendors.viewmodel.NewVendorViewModel


class NewVendor : DialogFragment() {
    private lateinit var binding: FragmentNewVendorBinding
    private var editRequest: AddRequest? = null
    private val viewModel: NewVendorViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")
        if (token.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "API Token is missing", Toast.LENGTH_SHORT).show()
            dismiss()
            throw IllegalStateException("API Token is missing")
        }
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
    ): View? {
        binding = FragmentNewVendorBinding.inflate(inflater, container, false)
        viewModel.isVendorAdded.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess == true) {
                Toast.makeText(context, "Vendor saved successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "Failed to save vendor", Toast.LENGTH_SHORT).show()
            }
        }

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
                        otherDetails = binding.otherdetails.text.toString(),
                        displayName = binding.displayname.text.toString()
                    )
                    viewModel.addVendor(vendor)

                } else {
                    val vendor = EditVRequest(
                        vendorName = binding.vendorname.text.toString(),
                        vendorPin = binding.vendorpin.text.toString(),
                        vendorType = binding.vendortype.text.toString(),
                        address = binding.address.text.toString(),
                        phone = binding.phonenumber.text.toString(),
                        email = binding.email.text.toString(),
                        otherDetails = binding.otherdetails.text.toString(),
                        displayName = binding.displayname.text.toString()
                    )
                    viewModel.updateVendor(vendor)
                }

            }
        }

return binding.root
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