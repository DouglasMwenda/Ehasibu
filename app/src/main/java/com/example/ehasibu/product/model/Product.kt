package com.example.ehasibu.product.model

import Add_Product
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ehasibu.AppModule
import com.example.ehasibu.databinding.FragmentProductBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.product.data.Adapter
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "product"

class Product : Fragment() {

    companion object {
        fun newInstance() = Product()
    }

    private lateinit var binding: FragmentProductBinding
    private lateinit var adapter: Adapter

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)

        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")

        // Initialize RecyclerView with a LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fetchProducts(binding, token!!)

        binding.addProductBtn.setOnClickListener {
            Log.d(TAG, "show us our frame")
            val fragment = Add_Product.newInstance()
            val trans = childFragmentManager.beginTransaction()
            trans.replace(binding.frameLayout.id, fragment).commit()
            binding.frameLayout.visibility = View.VISIBLE
        }

        binding.setPriceBtn.setOnClickListener {
        }


        return binding.root
    }

    private fun fetchProducts(binding: FragmentProductBinding, token: String) {
        Log.d(TAG, "fetchProducts: $token")
        val call = AppModule().getRetrofitInstance(token).getProducts()
        call.enqueue(object : Callback<ApiResponse<List<ProdResponse>>> {
            override fun onResponse(call: Call<ApiResponse<List<ProdResponse>>>, response: Response<ApiResponse<List<ProdResponse>>>) {
                Log.d(TAG, "products: ${response.body()}")

                if (response.isSuccessful) {
                    val products = response.body()?.entity ?: emptyList()
                    binding.recyclerView.apply{
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = Adapter(products)
                        setHasFixedSize(true)
                    }

                } else {
                    Toast.makeText(requireContext(), "Failed to fetch products", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<ProdResponse>>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
