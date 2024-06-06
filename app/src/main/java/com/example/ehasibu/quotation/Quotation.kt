package com.example.ehasibu.quotation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentQuotationBinding

class Quotation : Fragment() {
    private lateinit var binding: FragmentQuotationBinding
    private lateinit var newQuoteButton: Button
    private lateinit var quotesTableLayout: TableLayout
    private val viewModel: QuotationViewModel by viewModels()


    companion object {
        fun newInstance() = Quotation()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentQuotationBinding.inflate(inflater,container,false)
        newQuoteButton= binding.addnewquotebutton
        quotesTableLayout= binding.quotesTable
        newQuoteButton.setOnClickListener {
            val dialog= Newquotedialog()
            dialog.show(parentFragmentManager,"Newquotediaog")
        }
        return binding.root
    }
}