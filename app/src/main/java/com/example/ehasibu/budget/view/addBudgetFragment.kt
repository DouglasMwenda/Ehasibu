package com.example.ehasibu.budget.view

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
import com.example.ehasibu.budget.data.BudgetRequest
import com.example.ehasibu.budget.data.UpdateBudgetRequest
import com.example.ehasibu.budget.repo.BudgetRepository
import com.example.ehasibu.budget.viemodel.AddBudgetProvider
import com.example.ehasibu.budget.viemodel.AddBudgetViewModel
import com.example.ehasibu.databinding.FragmentAddBudgetBinding
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF


class AddBudgetFragment : DialogFragment() {
    private lateinit var binding: FragmentAddBudgetBinding
    private lateinit var budgetType: AutoCompleteTextView
    private lateinit var period: AutoCompleteTextView
    private var editRequest: UpdateBudgetRequest? = null


    private val viewModel: AddBudgetViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = BudgetRepository(token)
        AddBudgetProvider(repo)
    }


    companion object {
        private const val ARG_EDIT_REQUEST = "edit_request"
        fun newInstance(editRequest: UpdateBudgetRequest? = null): AddBudgetFragment {
            val fragment = AddBudgetFragment()
            val args = Bundle().apply {
                putParcelable(ARG_EDIT_REQUEST, editRequest)
            }
            fragment.arguments = args
            return AddBudgetFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            editRequest = it.getParcelable(ARG_EDIT_REQUEST)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBudgetBinding.inflate(inflater, container, false)
        budgetType = binding.budgetType
        val type = arrayOf(
            "Annual Budget",
            "Departmental Budget",
            "Project Budget",
            "Expense Budget",
            "Revenue Budget",
            "Cashflow Budget",
            "Flexible Budget"
        )
        val budgetAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, type)
        budgetType.setAdapter(budgetAdapter)
        period = binding.period
        val periods = arrayOf(
            "Annually",
            "Quarterly",
            "Monthly",
            "Weekly",
            "Daily",
            "Project Specific",
            "Flexible Budget"
        )
        val periodAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, periods)
        period.setAdapter(periodAdapter)

        viewModel.isBudgetAdded.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess == true) {
                Toast.makeText(context, "Budget added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to add budget", Toast.LENGTH_SHORT).show()
            }
        }

        binding.submitbudgetbutton.setOnClickListener {
            if (editRequest == null) {

                val budgetTypeText = binding.budgetType.text.toString()
                val amountBudgetedText = binding.amountbudgeted.text.toString()
                val descriptionText = binding.description.text.toString()
                val periodText = binding.period.text.toString()


                val amountBudgeted = if (amountBudgetedText.isNotEmpty()) {
                    amountBudgetedText.toIntOrNull() ?: 0
                } else {
                    0
                }

                val budget = BudgetRequest(
                    budgetType = budgetTypeText,
                    amountBudgeted = amountBudgeted,
                    description = descriptionText,
                    period = periodText
                )
                viewModel.addBudget(budget)
                dismiss()

            } else {
                val budgetTypeText = binding.budgetType.text.toString()
                val amountBudgetedText = binding.amountbudgeted.text.toString()
                val descriptionText = binding.description.text.toString()
                val periodText = binding.period.text.toString()

                val amountBudgeted = if (amountBudgetedText.isNotEmpty()) {
                    amountBudgetedText.toIntOrNull() ?: 0
                } else {
                    0
                }
                val budgetUpdate = UpdateBudgetRequest(
                    budgetId = editRequest!!.budgetId,
                    budgetType = budgetTypeText,
                    amountBudgeted = amountBudgeted,
                    amountSpent = editRequest!!.amountSpent,
                    budgetBalance = editRequest!!.budgetBalance,
                    date = editRequest!!.date,
                    deleted = editRequest!!.deleted,
                    description = descriptionText,
                    expenseModel = editRequest!!.expenseModel,
                    period = periodText
                )

                viewModel.editBudget(budgetUpdate)
            }
        }

        binding.deletebudgetButton.setOnClickListener {
            dismiss()

        }
        return binding.root
    }


    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}


