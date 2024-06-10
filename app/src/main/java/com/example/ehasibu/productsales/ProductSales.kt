package com.example.ehasibu.productsales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentProductsalesBinding

class ProductSales : Fragment() {

    private lateinit var binding:FragmentProductsalesBinding
    private lateinit var salesTableLayout: TableLayout
    private lateinit var Addsalebutton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentProductsalesBinding.inflate(inflater,container,false)
        salesTableLayout = binding.salestable
        Addsalebutton = binding.addsalebutton

        Addsalebutton.setOnClickListener {
            val dialog = Addsaledialog()
            dialog.show(parentFragmentManager,"Addsaledialog")
        }

        return binding.root
    }
    private fun updateSalesTable(sales: List<SalesItem>) {

        for (sale in sales) {
            val dataRow = TableRow(requireContext())

            val noValueTextView = binding.number
            val customerNameValueTextView = binding.salecustomername

            noValueTextView.text = sale.no
            customerNameValueTextView.text = sale.customerName

            salesTableLayout.addView(dataRow)
        }
    }

}