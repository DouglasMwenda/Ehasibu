package com.example.ehasibu.purchaseorder.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.databinding.FragmentAddPOBinding
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.purchaseorder.data.PoRequest
import com.example.ehasibu.purchaseorder.data.ProductX
import com.example.ehasibu.purchaseorder.repo.OrderRepo
import com.example.ehasibu.purchaseorder.viewmodel.AddPOProvider
import com.example.ehasibu.purchaseorder.viewmodel.AddPOViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.PREF

private const val TAG = "AddPO"

class AddPOFragment : DialogFragment() {
    private lateinit var binding: FragmentAddPOBinding

    companion object {
        fun newInstance() = AddPOFragment()
    }

    private val viewModel: AddPOViewModel by viewModels {
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val token = sharedPrefs.getString(API_TOKEN, "")
            ?: throw IllegalStateException("API Token is missing")
        val orderRepo = OrderRepo(token)
        val productRepo = ProductRepository(token)
        AddPOProvider(orderRepo, productRepo)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchProducts()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPOBinding.inflate(inflater, container, false)
        viewModel.isOrderAdded.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess == true) {
                Toast.makeText(context, "order added successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "Failed to add order", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.products.observe(viewLifecycleOwner) { products ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                products
            )
            binding.productnamefield.setAdapter(adapter)
            binding.productnamefield.setOnItemClickListener { _, _, position, _ ->
                val selectedProduct = products[position]
                binding.productnamefield.setText(selectedProduct.plus(Any()))
            }
        }


        binding.savepobutton.setOnClickListener {
            if (validateInput()) {
                val selectedProducts = listOf(
                    ProductX(
                        category = binding.category.text.toString(),
                        description = binding.description.text.toString(),
                        productId = "1", // This should be fetched from the backend or managed appropriately
                        productName = binding.productnamefield.text.toString(),
                        quantityPurchased = binding.quantitypurchased.text.toString().toIntOrNull()
                            ?: 0,
                        unit = binding.unit.text.toString()
                    )
                )

                val order = PoRequest(
                    deliveryDate = binding.deliverydate.text.toString(),
                    vendorId = binding.vendorname.text.toString(),
                    products = selectedProducts
                )

                viewModel.addOrder(order)

            }


        }
        binding.cancelpobutton.setOnClickListener {
            dismiss()

        }
        return binding.root

    }


    private fun validateInput(): Boolean {
        return when {
            binding.vendorname.text.isNullOrBlank() -> {

                Toast.makeText(context, "Vendor name is required", Toast.LENGTH_SHORT).show()
                false
            }
            /*  binding.deliverydate.text.isNullOrBlank() -> {
                  Toast.makeText(context, "Delivery date is required", Toast.LENGTH_SHORT).show()
                  false
              }
  */
            binding.productnamefield.text.isNullOrBlank() -> {
                Toast.makeText(context, "Product name is required", Toast.LENGTH_SHORT).show()
                false
            }

            else -> true

        }




    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}



