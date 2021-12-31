package com.example.contactappnextget.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.contactappnextget.R
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.model.ContactDao
import com.example.contactappnextget.room.ContactDatabase
import com.example.contactappnextget.viewModel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_contact.*
import kotlinx.android.synthetic.main.fragment_add_contact.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class AddContact : Fragment(R.layout.fragment_add_contact) {

    private lateinit var database: ContactDatabase
    private lateinit var contactDao: ContactDao

    private lateinit var name: String
    private lateinit var number: String
    private lateinit var address: String
    private lateinit var viewModel: ContactViewModel

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Create contact"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        view.create_contact.setOnClickListener {
            name = textInputEditText.text.toString().trim()
            number = textInputEditText2.text.toString().trim()
            address = textInputEditText3.text.toString().trim()

            when{
                name.isEmpty() -> textInputEditText.error = "Please enter the name"
                number.isEmpty() -> textInputEditText2.error = "Please enter the number"
                else -> {
                    val contact = Contact(name = name, number = number, address = address)
                    viewModel.saveContact(contact)
                }
            }
            findNavController().navigate(R.id.navigate_to_contactList)
        }
        return view
    }

    fun something(item: Contact){
        Log.i("Something", "username: ${item.name} number: ${item.number} address ${item.address}")
    }

}