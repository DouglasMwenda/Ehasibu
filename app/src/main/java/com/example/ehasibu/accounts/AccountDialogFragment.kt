package com.example.ehasibu.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.ehasibu.databinding.FragmentAccountDialogBinding
import com.google.android.material.textfield.TextInputEditText


class AccountDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAccountDialogBinding
    private lateinit var accountType: AutoCompleteTextView
    private lateinit var accountName: AutoCompleteTextView
    private lateinit var openingBalance: TextInputEditText
    private lateinit var submitAccountButton: Button
    private lateinit var cancelAccountButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountDialogBinding.inflate(inflater,container,false)
        accountType= binding.accountType
        accountName = binding.accountName
        openingBalance= binding.openingbalance
        submitAccountButton = binding.submitaccountbutton
        cancelAccountButton = binding.cancelaccountButton

        submitAccountButton.setOnClickListener{

        }
        cancelAccountButton.setOnClickListener{

        }

        return binding.root
    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        fun newInstance(): AccountDialogFragment {
            return AccountDialogFragment()
        }
    }

}