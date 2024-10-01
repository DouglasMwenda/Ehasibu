package com.example.ehasibu.accounts.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.accounts.data.AccountRequest
import com.example.ehasibu.accounts.repo.AccountRepo
import com.example.ehasibu.accounts.viewmodel.AddAccountProvider
import com.example.ehasibu.accounts.viewmodel.AddAccountViewmodel
import com.example.ehasibu.databinding.FragmentAccountDialogBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF


class AccountDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAccountDialogBinding
    private lateinit var accountType: AutoCompleteTextView
    private lateinit var accountName: AutoCompleteTextView


    private val viewModel: AddAccountViewmodel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = AccountRepo(token)
       AddAccountProvider(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountDialogBinding.inflate(inflater,container,false)


        viewModel.isAccountAdded.observe(viewLifecycleOwner) {isSuccess->

            if (isSuccess == true) {
                Toast.makeText(context, "Account added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to add account", Toast.LENGTH_SHORT).show()
            }

        }
        accountType= binding.accountType
        val type = arrayOf(
            "Current Assets",
            "Fixed Assets",
            "Current Liabilities",
            "Long-term liabilities",
            "Equity",
            "Non-Operating Expenses",
            "Operating Income",
            "Non-Operating Income"
        )
        val typeAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, type)
        accountType.setAdapter(typeAdapter)
        accountName = binding.accountName

        binding.submitaccountbutton.setOnClickListener {

            val accountName = binding.accountName.text.toString()
            val accountType = binding.accountType.text.toString()
            val accountBalance = binding.openingbalance.text.toString().toIntOrNull()

            if (accountBalance != null) {

                val accountAdd = AccountRequest(
                    accountName = accountName,
                    accountType = accountType,
                    accountBalance = accountBalance
                )
                viewModel.createAccount(accountAdd)
                dismiss()

            }
        }

        binding.cancelaccountButton.setOnClickListener{
            dismiss()

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