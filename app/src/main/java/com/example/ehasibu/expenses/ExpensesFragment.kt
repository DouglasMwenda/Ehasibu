package com.example.ehasibu.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentExpensesBinding

class ExpensesFragment : Fragment() {
    private lateinit var binding: FragmentExpensesBinding
    private lateinit var expenseButton: Button
    private lateinit var expensesTableLayout: TableLayout
    private val viewModel: ExpensesViewModel by viewModels()

    companion object {
        fun newInstance() = ExpensesFragment()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentExpensesBinding.inflate(inflater,container,false)
        expenseButton= binding.addexpensebutton
        expensesTableLayout= binding.expensestable

        expenseButton.setOnClickListener {
            val dialog = updateExpenseFragment()
            dialog.show(parentFragmentManager,"updateExpenseFragment")
        }
        return binding.root
    }
}