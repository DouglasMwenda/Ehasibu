package com.example.ehasibu.sevicesales

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentAddServicesalesBinding
import com.google.android.material.textfield.TextInputEditText

class AddServiceSalesFragment : DialogFragment() {
    private lateinit var binding: FragmentAddServicesalesBinding
    private lateinit var customerName: AutoCompleteTextView
    private lateinit var addProductButton:Button
    private lateinit var total: TextInputEditText
    private lateinit var invoiceButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddServicesalesBinding.inflate(inflater,container,false)
        customerName= binding.customerName
        addProductButton= binding.addproductbutton
        total= binding.Total
        invoiceButton= binding.invoicebutton
        backButton= binding.backbutton

        backButton.setOnClickListener {

        }
        return binding.root

    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}