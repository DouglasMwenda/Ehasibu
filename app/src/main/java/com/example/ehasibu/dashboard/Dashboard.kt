package com.example.ehasibu.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
                    R.id.nav_home -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_dashboard -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.sales -> {
                        handleSubMenu(menuItem)
                        true
                    }
                    R.id.purchases -> {
                        handleSubMenu(menuItem)
                        true
                    }
                    R.id.product_sales -> {
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
                        Toast.makeText(context, "Purchase Orders clicked", Toast.LENGTH_SHORT).show()
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
                    R.id.customerinformation -> {
                        Toast.makeText(context, "Customer Information clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_products -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_services -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_accounts -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_budget -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_expenses -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_reports -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_returns -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_settings -> {
                        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false

                }
            }
        }

        private fun handleSubMenu(menuItem: MenuItem) {
            if (menuItem.isChecked) {
                menuItem.isChecked = false
                collapseSubMenu(menuItem)
            } else {
                menuItem.isChecked = true
                expandSubMenu(menuItem)
            }
        }

        private fun expandSubMenu(menuItem: MenuItem) {
            for (i in 0 until menuItem.subMenu!!.size()) {
                menuItem.subMenu!!.getItem(i).isVisible = true
                menuItem.subMenu!!.getItem(i).isEnabled = true
            }
        }

        private fun collapseSubMenu(menuItem: MenuItem) {
            for (i in 0 until menuItem.subMenu!!.size()) {
                menuItem.subMenu!!.getItem(i).isVisible = false
                menuItem.subMenu!!.getItem(i).isEnabled = false
            }
        }
            }




