package com.example.ehasibu.vendors.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentVendorBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import com.example.ehasibu.vendors.moddel.EditVRequest
import com.example.ehasibu.vendors.moddel.VendorRepo
import com.example.ehasibu.vendors.viewmodel.VendorProvider
import com.example.ehasibu.vendors.viewmodel.VendorViewModel

private const val TAG = "vendor"

class Vendor : Fragment() {
    private lateinit var binding: FragmentVendorBinding


    private val vendorViewModel: VendorViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = VendorRepo(token)
        VendorProvider(repo)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): LinearLayout {
        binding = FragmentVendorBinding.inflate(inflater, container, false)
        vendorViewModel.vendors.observe(viewLifecycleOwner) { vendors ->
            if (vendors != null) {
                updateVendors(vendors)
            } else {
                Log.d(TAG, "No vendors to display")
            }
        }

        return binding.root

    }

    private fun updateVendors(vendors: List<com.example.ehasibu.vendors.moddel.Entity>) {
        val tableLayout = binding.vendorstable
        while (tableLayout.childCount > 1) {
            tableLayout.removeViewAt(1)
        }
        for (vendor in vendors) {
            val row = TableRow(context).apply { gravity = Gravity.CENTER_HORIZONTAL }

            val no = TextView(context).apply {
                text = vendor.vendorId
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))

            }
            val name = TextView(context).apply {
                text = vendor.vendorName
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val pin = TextView(context).apply {
                text = vendor.vendorPin
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val email = TextView(context).apply {
                text = vendor.email
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val phone = TextView(context).apply {
                text = vendor.phone
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val displayName = TextView(context).apply {
                text = vendor.displayName
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val vendorType = TextView(context).apply {
                text = vendor.vendorType
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))

            }
            val address = TextView(context).apply {
                text = vendor.address
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))


            }
            val otherInfo = TextView(context).apply {
                text = vendor.otherDetails
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))

            }

            val actionSpinner = Spinner(context).apply {
                gravity = Gravity.CENTER
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
                                val   editRequest = EditVRequest(
                                    vendor.address,
                                    vendor.displayName,
                                    vendor.email,
                                    vendor.otherDetails,
                                    vendor.phone,
                                    vendor.vendorName,
                                    vendor.vendorPin,
                                    vendor.vendorType

                                )
                                val dialog = NewVendor.newInstance(editRequest)
                                dialog.show(parentFragmentManager, "editProduct")
                            }

                            "Delete" -> {}/*deleteProduct(product.productId)*/
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
            row.addView(pin)
            row.addView(email)
            row.addView(phone)
            row.addView(displayName)
            row.addView(vendorType)
            row.addView(otherInfo)
            row.addView(address)
            row.addView(actionSpinner)
            tableLayout.addView(row)
        }
    }
}
