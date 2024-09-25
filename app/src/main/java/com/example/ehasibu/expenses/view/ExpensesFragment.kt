package com.example.ehasibu.expenses.view

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentExpensesBinding
import com.example.ehasibu.expenses.model.Entity
import com.example.ehasibu.expenses.repo.ExpenseRepo
import com.example.ehasibu.expenses.viewmodel.ExpenseProvider
import com.example.ehasibu.expenses.viewmodel.ExpensesViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG="expenses"
class Expenses : Fragment() {
    private lateinit var binding: FragmentExpensesBinding
    private lateinit var expenseButton: Button
    private lateinit var expensesTableLayout: TableLayout
    private val viewModel: ExpensesViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = ExpenseRepo(token)
        ExpenseProvider(repo)
    }

    companion object {
        fun newInstance() = Expenses()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpensesBinding.inflate(inflater, container, false)
        viewModel.expenses.observe(viewLifecycleOwner) { expenses ->


            if (expenses != null) {
                updateExpensesTable(expenses)
            }
        }
            expenseButton = binding.addexpensebutton
            expenseButton.setOnClickListener {
                val dialog = UpdateExpenseFragment()
                dialog.show(parentFragmentManager, "UpdateExpenseFragment")
            }
            return binding.root
        }


        private fun updateExpensesTable(expenses: List<Entity>) {

            val context = requireContext()
            val expensesTableLayout = binding.expensestable
            expensesTableLayout.removeViewsInLayout(1, expensesTableLayout.childCount - 1)

            for (expense in expenses) {
                val row = TableRow(context).apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                val no = TextView(context).apply {
                    text = expense.id.toString()
                    gravity= Gravity.CENTER
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                val budgetType = TextView(context).apply {
                    text = expense.budgetType
                    setTextColor(resources.getColor(R.color.black, null))
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.START
                }
                val date = TextView(context).apply {
                    text = expense.expenseDate
                    setTextColor(resources.getColor(R.color.black, null))
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.START
                }
                val amount = TextView(context).apply {
                    text = expense.amountSpent.toString()
                    setTextColor(resources.getColor(R.color.black, null))
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.START
                }
                val expenseCategory = TextView(context).apply {
                    text = expense.category
                    setTextColor(resources.getColor(R.color.black, null))
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.CENTER
                }
                val modeOfPayment = TextView(context).apply {
                    text = expense.modeOfPayment
                    setTextColor(resources.getColor(R.color.black, null))
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.CENTER
                }

                val Status = TextView(context).apply {
                    text = expense.status
                    setTextColor(resources.getColor(R.color.black, null))
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.CENTER
                }
                val expenseType = TextView(context).apply {
                    text = expense.expenseType
                    setTextColor(resources.getColor(R.color.black, null))
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.CENTER
                }

                val actionSpinner = Spinner(context).apply {
                    gravity = Gravity.START

                    adapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_item,
                        listOf("Action", "Edit", "Approve", "Pay", "Delete")
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
                                }

                                "Approve" -> {

                                }

                                "Reject" -> {

                                }

                                "Pay" -> {

                                }

                                "Delete" -> {
                                }

                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            // Do nothing
                        }
                    }
                    setBackgroundResource(android.R.drawable.btn_default)
                    setPadding(0, 0, 0, 0)

                }

                row.addView(no)
                row.addView(budgetType)
                row.addView(date)
                row.addView(amount)
                row.addView(expenseCategory)
                row.addView(modeOfPayment)
                row.addView(Status)
                row.addView(expenseType)
                row.addView(actionSpinner)
                expensesTableLayout.addView(row)
            }


        }
    }