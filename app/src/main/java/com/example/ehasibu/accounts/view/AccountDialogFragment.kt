package com.example.ehasibu.accounts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.fragment.app.DialogFragment
import com.example.ehasibu.databinding.FragmentAccountDialogBinding


class AccountDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAccountDialogBinding
    private lateinit var accountType: AutoCompleteTextView
    private lateinit var accountName: AutoCompleteTextView

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


        binding.submitaccountbutton.setOnClickListener{

        }
        binding.cancelaccountButton.setOnClickListener{

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