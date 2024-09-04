package com.example.ehasibu.budget.view

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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.budget.data.Entity
import com.example.ehasibu.budget.repo.BudgetRepository
import com.example.ehasibu.budget.viemodel.BudgetViewModel
import com.example.ehasibu.databinding.FragmentBudgetBinding
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.product.viewmodel.ProductProvider
import com.example.ehasibu.product.viewmodel.ProductViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

class BudgetFragment : Fragment() {
    private lateinit var binding: FragmentBudgetBinding
    private lateinit var addBudgetButton: Button
    private lateinit var budgetTableLayout: TableLayout

    companion object {
        fun newInstance() = BudgetFragment()
    }

    private val budgetViewModel: BudgetViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")!!
        val repo = BudgetRepository(token)
        BudgetViewModel.BudgetProvider(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBudgetBinding.inflate(inflater, container, false)
        budgetViewModel.budgets.observe(viewLifecycleOwner) { budgets->
            if (budgets !== null)
                updateBudgetTable(budgets)

        }

        binding.addbudgetbutton.setOnClickListener {
            val dialog = AddBudgetFragment()
            dialog.show(parentFragmentManager, "AddBudgetFragment")
        }
        return binding.root

    }


    private fun updateBudgetTable(budgets: List<Entity>) {

        val tableLayout = binding.budgetTable

        while (tableLayout.childCount > 1) {
            tableLayout.removeViewAt(1)
        }

        for(budget in budgets) {
            val row = TableRow(context).apply { gravity = Gravity.CENTER_HORIZONTAL }
            val no = TextView(context).apply {
                text= budget.budgetId.toString()
                setTextColor(resources.getColor(R.color.black, null))
            }

            val type = TextView(context).apply {
                text = budget.budgetType
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val description = TextView(context).apply {
                text = budget.description
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val amountBudgeted = TextView(context).apply {
                text = budget.amountBudgeted.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }
            val amountSpent = TextView(context).apply {
                text = budget.amountSpent.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val period = TextView(context).apply {
                text = budget.period
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val date = TextView(context).apply {
                text = budget.date
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }

            val balance = TextView(context).apply {
                text = budget.budgetBalance.toString()
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.black, null))
            }


            val actionSpinner = Spinner(context).apply {
                gravity = Gravity.START
                adapter = ArrayAdapter(
                    context,
                    android.R.layout.simple_spinner_item,
                    listOf("Action", "Edit", "Delete")
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

                            "Delete" -> {}
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
            row.addView(type)
            row.addView(description)
            row.addView(amountBudgeted)
            row.addView(amountSpent)
            row.addView(period)
            row.addView(date)
            row.addView(balance)
            row.addView(actionSpinner)
        }

    }
}