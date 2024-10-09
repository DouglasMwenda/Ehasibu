package com.example.ehasibu.expenses.view

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentAddExpenseBinding
import com.example.ehasibu.expenses.model.EditExpRequest
import com.example.ehasibu.expenses.model.ExpenseRequest
import com.example.ehasibu.expenses.repo.ExpenseRepo
import com.example.ehasibu.expenses.viewmodel.AddExpProvider
import com.example.ehasibu.expenses.viewmodel.AddExpenseViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
private const val TAG = "addexpense"

class AddExpense : DialogFragment() {
    private lateinit var binding: FragmentAddExpenseBinding
    private var editRequest: EditExpRequest? = null
    private lateinit var expenseType: AutoCompleteTextView
    private lateinit var budgetType: AutoCompleteTextView

    private val viewModel: AddExpenseViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = ExpenseRepo(token)
        AddExpProvider(repo)

    }

    companion object {
        private const val ARG_EDIT_REQUEST = "edit_request"

        fun newInstance(editRequest: EditExpRequest? = null): AddExpense {
            val fragment = AddExpense()
            val args = Bundle().apply {
                putParcelable(ARG_EDIT_REQUEST, editRequest)
            }
            fragment.arguments = args
            return fragment
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
        binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        viewModel.isExpenseAdded.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess == true) {
                Log.d(TAG, "Expense added successfully ${isSuccess}")
                Toast.makeText(context, "Expense added successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "Failed to add expense", Toast.LENGTH_SHORT).show()
            }
        }
        expenseType = binding.expensetype
        val expenseTypes = arrayOf("OPERATING_EXPENSES", "NON_OPERATING_EXPENSES")
        val adapter =
            ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, expenseTypes)
        expenseType.setAdapter(adapter)

        budgetType = binding.budggetType
        val budgetTypes = arrayOf(
            "Annual",
            "Expenses",
            "Departmental",
            "Project",
            "Revenue",
            "Cashflow",
            "Flexible"
        )
        val adapter2 =
            ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, budgetTypes)
        budgetType.setAdapter(adapter2)

        val category = binding.category
        val categories = mapOf(
            "Operating expenses" to arrayOf(
                "Salaries",
                "Bank Charges",
                "Tax", "Insurance",
                "Meals and Entertainment",
                "Rent",
                "Utilities",
                "Advertisement",
                "Office Expense",
                "Shipping and Delivery",
                "Travel",
                "Salaries and Wages"
            ),
            "Non-operating expenses" to arrayOf(
                "Interest", "Losses on Sale of Assets"
            )
        )

        val selectedCategory = "Operating expenses"
        val subcategories = categories[selectedCategory] ?: emptyArray()

        val adapter3 =
            ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, subcategories)
        category.setAdapter(adapter3)

        binding.submitexpensebutton.setOnClickListener {
            if (validateInput()) {
                if (editRequest == null) {

                    val expense = ExpenseRequest(
                        expenseType = binding.expensetype.text.toString(),
                        budgetType = binding.budggetType.text.toString(),
                        category = binding.category.text.toString(),
                        amountSpent = binding.amountspent.text.toString().toDouble(),
                        modeOfPayment = binding.modeOfPayment.text.toString()

                    )
                    viewModel.addExpense(expense)

                } else {
                    val expense = ExpenseRequest(
                        expenseType = binding.expensetype.text.toString(),
                        budgetType = binding.budggetType.text.toString(),
                        category = binding.category.text.toString(),
                        amountSpent = binding.amountspent.text.toString().toDouble(),
                        modeOfPayment = binding.modeOfPayment.text.toString()
                    )
                    //  viewModel.updateExpense(editRequest.id, expense)

                }
            }

        }
        binding.cancelExpenseButton.setOnClickListener {
            dismiss()
        }

        editRequest?.let {
            binding.expensetype.setText(it.expenseType)
            binding.budggetType.setText(it.budgetType)
            binding.category.setText(it.category)
            binding.amountspent.setText(it.amountSpent.toString())
            binding.modeOfPayment.setText(it.modeOfPayment)
            binding.cancelExpenseButton.visibility = View.VISIBLE
        }

        return binding.root
    }



        private fun validateInput(): Boolean {
            return when {
                binding.category.text.isNullOrBlank() -> {
                    Toast.makeText(context, "Category is required", Toast.LENGTH_SHORT).show()
                    false
                }

                binding.expensetype.text.isNullOrBlank() -> {
                    Toast.makeText(context, "expensetype is required", Toast.LENGTH_SHORT).show()
                    false
                }

                binding.budggetType.text.isNullOrBlank() -> {
                    Toast.makeText(context, "budget type is required", Toast.LENGTH_SHORT).show()
                    false
                }

                binding.modeOfPayment.text.isNullOrBlank() -> {
                    Toast.makeText(context, "mode of payment is required", Toast.LENGTH_SHORT)
                        .show()
                    false
                }

                binding.amountspent.text.isNullOrBlank() -> {
                    Toast.makeText(context, "Amount spent is required", Toast.LENGTH_SHORT).show()
                    false
                }


                else -> true

            }

        }


    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }


}