package com.example.ehasibu.product.model

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ehasibu.R
import com.example.ehasibu.product.viewmodel.AddProductViewModel

class Add_Product : Fragment() {

    companion object {
        fun newInstance() = Add_Product()
    }

    private val viewModel: AddProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add__product, container, false)
    }
}