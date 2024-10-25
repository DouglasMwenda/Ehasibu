package com.example.ehasibu.productsales.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentPaymentBinding
import com.example.ehasibu.productsales.viewModel.PaymentViewModel

class PaymentFragment : DialogFragment() {
    private lateinit var binding: FragmentPaymentBinding
    private val viewModel: PaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize UI elements
        val modeOfPaymentEditText = binding.modeOfPayment
        val amountPayableEditText = binding.amountPayable
        val amountReceivedEditText = binding.amountReceived
        val amountPaidEditText = binding.amountPaid
        val balanceEditText = binding.balance
        val submitButton = binding.submitpaymentbutton
        val cancelButton = binding.cancelpaymentbutton

        // Handle the payment process for cash payment
        submitButton.setOnClickListener {
            // Logic to handle payment submission
            val amountPayable = amountPayableEditText.text.toString().toDoubleOrNull()
            val amountReceived = amountReceivedEditText.text.toString().toDoubleOrNull()

            if (amountPayable != null && amountReceived != null) {
                val balance = amountReceived - amountPayable
                balanceEditText.setText(balance.toString())

                dismiss()
            } else {
                // Handle error (invalid input)
            }
        }

        cancelButton.setOnClickListener {
            // Close the dialog
            dismiss()
        }
    }
}
