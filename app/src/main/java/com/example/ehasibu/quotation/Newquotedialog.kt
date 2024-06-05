package com.example.ehasibu.quotation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentNewquotedialogBinding


class Newquotedialog : Fragment() {
    private lateinit var binding: FragmentNewquotedialogBinding
    private lateinit var customerName: AutoCompleteTextView
    private lateinit var productsTableLayout: TableLayout
    private lateinit var summaryTotal: EditText
    private lateinit var createQuoteButton: Button
    private lateinit var backButton: Button
    private lateinit var approveQuoteButton: Button
    private lateinit var rejectQuoteButton: Button
    private lateinit var printQuoteButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewquotedialogBinding.inflate(inflater,container,false)
        customerName = binding.customerNameField
        productsTableLayout = binding.quoteproducttable
        summaryTotal = binding.quoteSummaryTotal
        createQuoteButton=binding.createQuotebutton
        backButton = binding.quotebackButton
        approveQuoteButton = binding.approvequoteButton
        rejectQuoteButton = binding.rejectquoteButton
        printQuoteButton = binding.printquoteButton

        createQuoteButton.setOnClickListener {

        }
        backButton.setOnClickListener {

        }
        approveQuoteButton.setOnClickListener {

        }
        rejectQuoteButton.setOnClickListener {

        }
        printQuoteButton.setOnClickListener {

        }

        return binding.root
    }





}