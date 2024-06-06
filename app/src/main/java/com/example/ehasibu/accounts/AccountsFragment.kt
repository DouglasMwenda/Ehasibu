package com.example.ehasibu.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentAccountsBinding

class AccountsFragment : Fragment() {
    private lateinit var binding: FragmentAccountsBinding
    private lateinit var addAccountButton: Button
    private lateinit var accountsTableLayout: TableLayout
    private val viewModel: AccountsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAccountsBinding.inflate(inflater,container,false)
        addAccountButton = binding.addaccountsbutton
        accountsTableLayout = binding.accountsTable
        addAccountButton.setOnClickListener{
            val dialog= AccountDialogFragment()
            dialog.show(parentFragmentManager,"AccountDialogFragment")
        }
        return binding.root

    }
}