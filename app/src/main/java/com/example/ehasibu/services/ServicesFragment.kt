package com.example.ehasibu.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {
    private lateinit var binding: FragmentServicesBinding

    companion object {
        fun newInstance() = ServicesFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServicesBinding.inflate(inflater, container, false)


        binding.addaservicebutton.setOnClickListener {
            val dialog = AddServiceFragment()
            dialog.show(parentFragmentManager, "{AddServiceFragment")
        }

        return binding.root


    }
}