package com.example.contactappnextget.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactappnextget.R


class Detailing : Fragment() {

    private val args: DetailingArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailing, container, false)
        val name = view.findViewById<TextView>(R.id.name)
        val phone = view.findViewById<TextView>(R.id.phone)
        val address = view.findViewById<TextView>(R.id.address)
        val edit = view.findViewById<FrameLayout>(R.id.edit)

        name.text = args.name
        phone.text = args.phone
        address.text = args.address

        edit.setOnClickListener {
            val action = DetailingDirections.actionDetailingToEditContact(args.name, args.phone, args.address)
            findNavController().navigate(action)
        }

        return view
    }

}