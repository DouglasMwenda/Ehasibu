package com.example.ehasibu.product.model

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ehasibu.databinding.ProductsfloorBinding
import com.example.ehasibu.product.data.ProdResponse

private const val TAG1 = "params"
class ProductFloorFragment: Fragment (){
    private lateinit var binding: ProductsfloorBinding
    val args: ProductFloorFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductsfloorBinding.inflate(inflater, container, false)
        val product = arguments?.getSerializable("product") as? ProdResponse

        Log.d(TAG1, product.toString())
        return binding.root
    }
}