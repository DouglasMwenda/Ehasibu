package com.example.ehasibu.sevicesales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentServiceSalesBinding

class ServiceSalesFragment : Fragment(){
private lateinit var binding: FragmentServiceSalesBinding
    private lateinit var addSaleButton: Button
    private val viewModel: ServiceSalesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentServiceSalesBinding.inflate(inflater,container,false)
        addSaleButton= binding.addservicesalebutton

        addSaleButton.setOnClickListener {
            val dialog= AddServiceSalesFragment()
            dialog.show(parentFragmentManager,"AddServiceSalesFragment")
        }
        return binding.root
    }
}