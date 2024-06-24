package com.example.ehasibu.product.data
/*

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ehasibu.R
import com.example.ehasibu.databinding.ProductsRecViewBinding
import com.google.android.gms.analytics.ecommerce.Product


class Adapter(private val products: List<ProdResponse>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(val binding: ProductsRecViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            //fetchAllProducts
        fun bind(products: ProdResponse) {
            binding.productName.text = products.productName
                binding.productDescription.text = products.description
            binding.sellP.text = products.buyingPrice.toString()
         //   binding.productImage.setImageResource(products.productImage)

            binding.root.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("productId", products.productId)
                }
                it.findFragment<Product>().findNavController().navigate(R.id.action_products_to_productFloorFragment, bundle)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProductsRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}

*/

