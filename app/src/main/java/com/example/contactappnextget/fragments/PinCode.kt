package com.example.contactappnextget.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.contactappnextget.R
import com.example.contactappnextget.databinding.FragmentPinBinding
import com.example.contactappnextget.model.Pin
import com.example.contactappnextget.viewModel.ContactViewModel
import com.example.contactappnextget.viewModel.PinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class PinCode : Fragment() {

    private lateinit var pinViewModel: PinViewModel
    private lateinit var binding: FragmentPinBinding
    private var count by Delegates.notNull<Int>()
    private lateinit var number: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPinBinding.inflate(inflater, container, false)
        count = 0

        pinViewModel = ViewModelProvider(this)[pinViewModel::class.java]
        binding.one.setOnClickListener {
            count++
            number += "1"
        }
        binding.two.setOnClickListener {
            count++
            number += "2"
        }
        binding.three.setOnClickListener {
            count++
            number += "3"
        }
        binding.four.setOnClickListener {
            count++
            number += "4"
        }
        binding.five.setOnClickListener {
            count++
            number += "5"
        }
        binding.six.setOnClickListener {
            count++
            number += "6"
        }
        binding.seven.setOnClickListener {
            count++
            number += "7"
        }
        binding.eight.setOnClickListener {
            count++
            number += "8"
        }
        binding.nine.setOnClickListener {
            count++
            number += "9"
        }

        if(count == 4) {
            val pin = Pin(0, number)
            pinViewModel.insertPin(pin)
        }


        return inflater.inflate(R.layout.fragment_pin, container, false)
    }

}