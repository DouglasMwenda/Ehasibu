package com.example.ehasibu.services

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.ehasibu.databinding.FragmentAddServiceBinding


class AddServiceFragment : DialogFragment() {
    private lateinit var binding: FragmentAddServiceBinding
    private lateinit var serviceName:EditText
    private lateinit var description:EditText
    private lateinit var servicePrice:EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddServiceBinding.inflate(inflater,container,false)
        serviceName= binding.servicename
        description= binding.description
        servicePrice= binding.serviceprice
        saveButton= binding.saveservicebutton
        cancelButton= binding.cancelservicebutton

        saveButton.setOnClickListener {

        }

        cancelButton.setOnClickListener {

        }
         return binding.root
    }


    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        fun newInstance(): AddServiceFragment {
            return AddServiceFragment()
        }
    }
}