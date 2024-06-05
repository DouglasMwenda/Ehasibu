package com.example.ehasibu.customerinformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentCustomersBinding

class Customers : Fragment() {
    private lateinit var binding: FragmentCustomersBinding
    private lateinit var addCustomerButton: Button
    private lateinit var customersTableLayout: TableLayout
    private val viewModel: CustomersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomersBinding.inflate(inflater,container,false)
        addCustomerButton = binding.addcustomerbutton
        customersTableLayout = binding.customerstable
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}