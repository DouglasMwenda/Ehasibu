package com.example.ehasibu.expenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.ehasibu.R
import com.example.ehasibu.budget.addBudgetFragment
import com.example.ehasibu.databinding.FragmentUpdateExpenseBinding


class updateExpenseFragment : DialogFragment() {
    private lateinit var binding: FragmentUpdateExpenseBinding
    private lateinit var expenseType: AutoCompleteTextView
    private lateinit var budgetType: AutoCompleteTextView
    private lateinit var category: AutoCompleteTextView
    private lateinit var amountSpent: EditText
    private lateinit var submitButton: Button
    private lateinit var deleteButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUpdateExpenseBinding.inflate(inflater,container,false)
        expenseType = binding.expensetype
        budgetType= binding.budggetType
        category= binding.category
        amountSpent= binding.amountspent
        submitButton= binding.submitexpensebutton
        deleteButton= binding.deleteexpenseButton

        submitButton.setOnClickListener {

        }
        deleteButton.setOnClickListener {
            dismissAllowingStateLoss()

        }

        return binding.root

    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        fun newInstance(): updateExpenseFragment {
            return updateExpenseFragment()
        }
    }


}