package com.example.contactappnextget.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.contactappnextget.R
import com.google.android.material.textfield.TextInputEditText


class EditContact : Fragment() {
    private val args: EditContactArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_contact, container, false)

        val name = view.findViewById<TextInputEditText>(R.id.name)
        val phone = view.findViewById<TextInputEditText>(R.id.phone)
        val address = view.findViewById<TextInputEditText>(R.id.address)
        name.setText(args.name)
        phone.setText(args.phone)
        address.setText(args.address)

        return view
    }

}