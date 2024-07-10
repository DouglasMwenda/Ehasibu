package com.example.ehasibu.purchaseorder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.purchaseorder.viewmodel.PurchaseOrderViewModel

class Purchase_Order : Fragment() {

    companion object {
        fun newInstance() = Purchase_Order()
    }

    private val viewModel: PurchaseOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_purchase_order, container, false)
    }
}