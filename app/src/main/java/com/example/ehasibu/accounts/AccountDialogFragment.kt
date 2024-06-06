package com.example.ehasibu.accounts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.EditText
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentAccountDialogBinding


class AccountDialogFragment : Fragment() {
    private lateinit var binding: FragmentAccountDialogBinding
    private lateinit var accountType: AutoCompleteTextView
    private lateinit var accountName: AutoCompleteTextView
    private lateinit var openingBalance: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountDialogBinding.inflate(inflater,container,false)
        return binding.root
    }



}