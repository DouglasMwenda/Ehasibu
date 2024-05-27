package com.example.ehasibu.customerinformation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentCustomersBinding
import com.example.ehasibu.databinding.FragmentDashboardBinding

class Customers : Fragment() {
    private lateinit var binding: FragmentCustomersBinding

    companion object {
        fun newInstance() = Customers()
    }

    private val viewModel: CustomersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCustomersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tableLayout = binding.customertable

        val row = TableRow(requireContext())
        val column1 = TextView(requireContext()).apply { text = "Column 1" }
        val column2 = TextView(requireContext()).apply { text = "Column 2" }
        row.addView(column1)
        row.addView(column2)
        tableLayout.addView(row)

    }
}