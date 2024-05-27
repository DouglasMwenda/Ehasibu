package com.example.ehasibu.dashboard

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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


        toggle = ActionBarDrawerToggle(requireActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)


        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        setupNavigationView(navView)
    }

    private fun setupNavigationView(navView: NavigationView) {

    }

}