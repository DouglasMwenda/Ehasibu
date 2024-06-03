package com.example.ehasibu.dashboard

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        drawerLayout = binding.drawerLayout
        navView = binding.navView
        val toolbar = binding.toolbar

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

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
            Log.d("Menudebug", "Menu item ID: ${menuItem.itemId}")
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_dashboard -> {
                    Toast.makeText(context, "Dashboard clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_sales -> {

                    true
                }
                R.id.nav_purchases -> {
                    Toast.makeText(context, "Purchases clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_products -> {
                    Toast.makeText(context, "Products clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_services -> {
                    Toast.makeText(context, "Services clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_accounts -> {
                    Toast.makeText(context, "Accounts clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_budget -> {
                    Toast.makeText(context, "Budget clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_expenses -> {
                    Toast.makeText(context, "Expenses clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_reports -> {
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
            }
        }
    }


}
