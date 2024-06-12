package com.example.ehasibu.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentDashboardBinding
import com.google.android.material.navigation.NavigationView

class Dashboard : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    companion object {
        fun newInstance() = Dashboard()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
    }


        private fun setupNavigationView(navView: NavigationView) {
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    /*   R.id.nav_home -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }*/
                    R.id.nav_dashboard -> {
                        Toast.makeText(context, "Dashboard clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.nav_sales -> {

                      //  setupSalesSpinner()
                        findNavController().navigate(R.id.productSales)

                       showSalesSpinner()


                        true
                    }

                    R.id.nav_purchases -> {
                        findNavController().navigate(R.id.purchase_Order)

                        Toast.makeText(context, "Purchase Orders clicked", Toast.LENGTH_SHORT)
                            .show()
                        true

                    }
                    /*  R.id.product_sales -> {
                        Toast.makeText(context, "Product Sales clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.service_sales -> {
                        Toast.makeText(context, "Service Sales clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.quotation -> {
                        Toast.makeText(context, "Quotation clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.purchase_orders -> {
                        findNavController().navigate(R.id.services)

                        Toast.makeText(context, "Purchase Orders clicked", Toast.LENGTH_SHORT)
                            .show()
                        true
                    }
                     R.id.purchasebill -> {
                        Toast.makeText(context, "Purchase Bill clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.vendor -> {
                        Toast.makeText(context, "Vendor clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    */
                    R.id.nav_products -> {
                        findNavController().navigate(R.id.products)

                        Toast.makeText(context, "Products clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.nav_services -> {
                        findNavController().navigate(R.id.services)

                        Toast.makeText(context, "Services clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.nav_accounts -> {
                        findNavController().navigate(R.id.accounts)
                        Toast.makeText(context, "Accounts clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.nav_budget -> {

                        findNavController().navigate(R.id.budget)

                        Toast.makeText(context, "Budget clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.nav_expenses -> {
                        findNavController().navigate(R.id.expenses)

                        Toast.makeText(context, "expenses clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.nav_reports -> {
                        findNavController().navigate(R.id.report)

                        Toast.makeText(context, "Reports clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.nav_returns -> {

                        Toast.makeText(context, "Returns clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.nav_settings -> {
                        Toast.makeText(context, "Settings clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false

                }.also {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
        }

    private fun showSalesSpinner() {
        val context = requireContext()

        val spinner = Spinner(context)
        val salesItems = resources.getStringArray(R.array.sales_spinner_items)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, salesItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                handleSpinnerSelection(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }

        }

        spinner.performClick()
    }

    private fun handleSpinnerSelection(position: Int) {
        val context = requireContext()
        when (position) {
            0 -> Toast.makeText(context, "Product Sales Selected", Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(context, "Service Sales Selected", Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(context, "Quotation Selected", Toast.LENGTH_SHORT).show()
            3 -> Toast.makeText(context, "Customer Information Selected", Toast.LENGTH_SHORT).show()
        }
    }
}




