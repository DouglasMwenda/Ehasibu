package com.example.ehasibu.product.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentSetpriceBinding




class Set_Price : DialogFragment() {
  private lateinit var binding: FragmentSetpriceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setprice, container, false)
    }


}