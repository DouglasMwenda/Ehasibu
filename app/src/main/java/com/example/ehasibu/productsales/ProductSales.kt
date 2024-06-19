package com.example.ehasibu.productsales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import androidx.fragment.app.Fragment
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

        Addsalebutton = binding.addsalebutton

        Addsalebutton.setOnClickListener {
            val dialog = Addsaledialog()
            dialog.show(parentFragmentManager,"Addsaledialog")
        }

        return binding.root
    }
    private fun updateSalesTable(sales: List<SalesItem>) {




        }
    }

