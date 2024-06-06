package com.example.ehasibu.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.ehasibu.databinding.FragmentAddBudgetBinding


class addBudgetFragment : DialogFragment() {
    private lateinit var binding: FragmentAddBudgetBinding
    private lateinit var budgetType: AutoCompleteTextView
    private lateinit var description:EditText
    private lateinit var amountBudgeted:EditText
    private lateinit var amountSpent: EditText
    private lateinit var period: AutoCompleteTextView
    private lateinit var submitButton: Button
    private lateinit var deleteButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddBudgetBinding.inflate(inflater,container,false)
        budgetType = binding.budgetType
        description = binding.description
        amountBudgeted = binding.amountbudgeted
        amountSpent = binding.amountspent
        period= binding.period
        submitButton=binding.submitbudgetbutton
        deleteButton= binding.deletebudgetButton

        submitButton.setOnClickListener {

        }

        deleteButton.setOnClickListener {

        }
        return binding.root
    }


    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        fun newInstance(): addBudgetFragment {
            return addBudgetFragment()
        }
    }


    }


