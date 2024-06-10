package com.example.ehasibu.productsales

import android.app.Dialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentPaymentBinding

class PaymentFragment : DialogFragment() {
    private lateinit var binding:FragmentPaymentBinding
    private val viewModel: PaymentViewModel by viewModels()
    private lateinit var modeOfPaymentEditText: EditText
    private lateinit var amountPayableEditText: EditText
    private lateinit var amountReceivedEditText: EditText
    private lateinit var amountPaidEditText: EditText
    private lateinit var balanceEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button


    companion object {
        fun newInstance(
            customerName: String,
            total: Double,
            tax: Double,
            netTotal: Double,
            paidAmount: Double
        ) = PaymentFragment()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater,container,false)
        modeOfPaymentEditText= binding.modeOfPayment
        amountPayableEditText = binding.amountPayable
        amountReceivedEditText= binding.amountReceived
        amountPaidEditText = binding.amountPaid
        balanceEditText= binding.balance
        submitButton = binding.submitpaymentbutton
        cancelButton = binding.cancelpaymentbutton


        submitButton.setOnClickListener{


        }
        cancelButton.setOnClickListener {
            dismiss()

        }


        return binding.root
    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}