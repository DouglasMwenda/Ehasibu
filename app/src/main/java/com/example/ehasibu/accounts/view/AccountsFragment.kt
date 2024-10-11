package com.example.ehasibu.accounts.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.accounts.data.AccountsEntity
import com.example.ehasibu.accounts.repo.AccountRepo
import com.example.ehasibu.accounts.viewmodel.AccountsProvider
import com.example.ehasibu.accounts.viewmodel.AccountsViewModel
import com.example.ehasibu.customerinformation.view.TAG
import com.example.ehasibu.databinding.FragmentAccountsBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

class AccountsFragment : Fragment() {
    private lateinit var binding: FragmentAccountsBinding


    private val accountsViewModel: AccountsViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = AccountRepo(token)
        AccountsProvider(repo)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAccountsBinding.inflate(inflater,container,false)


        binding.addaccountsbutton.setOnClickListener{
            val dialog= AccountDialogFragment()
            dialog.show(parentFragmentManager,"AccountDialogFragment")
        }

        accountsViewModel.account.observe(viewLifecycleOwner){ accounts->
            if (accounts != null){
                updateAccountsTable(accounts)}
            else{
                Log.d(TAG, "No accounts to display")
            }
        }
        return binding.root

    }
    private fun updateAccountsTable (accounts : List<AccountsEntity>) {
        val accountsTable = binding.accountsTable


        for (account in accounts) {
            val row = TableRow(context).apply { gravity = Gravity.CENTER_HORIZONTAL }
            val code = TextView(context).apply {
                text = account.accountCode.toString()
                setTextColor(resources.getColor(R.color.black, null))
            }
            val name = TextView(context).apply {
                text = account.accountName
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val type = TextView(context).apply {
                text = account.accountType
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val balance = TextView(context).apply {
                text = account.accountBalance.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val description = TextView(context).apply {
                text = account.description
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val actionSpinner = Spinner(context).apply {
                gravity = Gravity.START
                adapter = ArrayAdapter(
                    context,
                    android.R.layout.simple_spinner_item,
                    listOf("Action", "Approve", "Delete")
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        val action = parent.getItemAtPosition(position) as String
                        when (action) {
                            "Edit" -> {
                                // Handle edit action
                            }

                            "Delete" -> deleteAccount(account.accountCode)

                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // Do nothing
                    }
                }
                setBackgroundResource(android.R.drawable.btn_default)
                setPadding(0, 0, 0, 0)
            }

            row.addView(code)
            row.addView(name)
            row.addView(type)
            row.addView(balance)
            row.addView(description)
            row.addView(actionSpinner)
            accountsTable.addView(row)

        }

    }

    private fun deleteAccount(accountCode: Int) {
        accountsViewModel.deleteAccount(accountCode)
    }
}