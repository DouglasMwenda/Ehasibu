package com.example.ehasibu.dashboard

import AddProduct
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ehasibu.R
import com.example.ehasibu.customerinformation.view.CustomerDialog
import com.example.ehasibu.databinding.FragmentDashboardBinding
import com.example.ehasibu.productsales.view.Addsaledialog
import com.example.ehasibu.quotation.Newquotedialog
import com.example.ehasibu.vendors.view.NewVendor
import com.google.android.material.navigation.NavigationView


class Dashboard : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle


    companion object {
        private const val REQUEST_CODE_PERMISSION = 100
        fun newInstance() = Dashboard()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        this.binding = binding

        binding.salecard.setOnClickListener {
            val dialog = Addsaledialog()
            dialog.show(parentFragmentManager, "Addsaledialog")
        }
        binding.productCard.setOnClickListener {
            val dialog = AddProduct()
            dialog.show(parentFragmentManager, "AddProduct")
        }
        binding.quotecard.setOnClickListener {
            val dialog = Newquotedialog()
            dialog.show(parentFragmentManager, "Newquotedialog")
        }
        binding.customerCard.setOnClickListener {
            val dialog = CustomerDialog()
            dialog.show(parentFragmentManager, "CustomerDialog")
        }
        binding.vendorcard.setOnClickListener {
            val dialog = NewVendor()
            dialog.show(parentFragmentManager, "NewVendor")

        }
        binding.purchasecard.setOnClickListener { }

        binding.profile.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                   android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_PERMISSION
                )
            } else {
                pickImageFromGallery()
            }
        }


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        drawerLayout = binding.drawerLayout
        navView = binding.navView
        val toolbar = binding.toolbar


        toggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )


        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        setupNavigationView(navView)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    showExitConfirmationDialog()
                }
            })
    }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                binding.profile.setImageURI(it)
            }
        }

    private fun pickImageFromGallery() {
        pickImageLauncher.launch("image/*")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery()
            }
        }
    }


    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit the app?")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                requireActivity().finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    private fun setupNavigationView(navView: NavigationView) {
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
               /* R.id.nav_home -> {
                    findNavController().navigate(R.id.home)
                    true
                }

                R.id.nav_dashboard -> {

                    true
                }*/

                R.id.nav_productsales -> {
                      findNavController().navigate(R.id.productSales)
                    true
                }
                R.id.nav_servicesales-> {
                    findNavController().navigate(R.id.serviceSales)
                    true
                }

                R.id.nav_quotation-> {
                    findNavController().navigate(R.id.quotation2)
                    true
                }


                R.id.nav_customers -> {
                    findNavController().navigate(R.id.customers)
                    true
                }
                R.id.nav_purchaseorders -> {
                    findNavController().navigate(R.id.purchase_Order)

                    true
                }

                R.id.nav_bills -> {
                     findNavController().navigate(R.id.bill)
                    true
                }

                R.id.nav_Vendors -> {
                    findNavController().navigate(R.id.vendor)
                    true
                }


                R.id.nav_products -> {
                    findNavController().navigate(R.id.products)

                    true
                }

                R.id.nav_services -> {
                    findNavController().navigate(R.id.services)

                    true
                }

                R.id.nav_accounts -> {
                    findNavController().navigate(R.id.accounts)

                    true
                }

                R.id.nav_bills->{

                    findNavController().navigate(R.id.bills)

                    true
                }

                R.id.nav_budget -> {

                    findNavController().navigate(R.id.budget)

                    true
                }

                R.id.nav_expenses -> {
                    findNavController().navigate(R.id.expenses)

                    true
                }

                R.id.nav_reports -> {
                    findNavController().navigate(R.id.report)

                    true
                }

                R.id.nav_returns -> {

                    true
                }

                R.id.nav_settings -> {

                    true
                }


                else -> false

            }.also {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }


}




