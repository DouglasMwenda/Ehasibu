package com.example.ehasibu.purchaseorder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.purchaseorder.viewmodel.AddPOViewModel

class AddPOFragment : DialogFragment() {

    companion object {
        fun newInstance() = AddPOFragment()
    }

    private val viewModel: AddPOViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_p_o, container, false)
    }
}