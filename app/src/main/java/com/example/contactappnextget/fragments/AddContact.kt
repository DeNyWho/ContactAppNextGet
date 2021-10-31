package com.example.contactappnextget.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.contactappnextget.R
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.model.ContactDao
import com.example.contactappnextget.room.ContactDataBase
import com.example.contactappnextget.viewModel.ContactViewModel
import com.example.contactappnextget.viewModel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_add_contact.*
import kotlinx.android.synthetic.main.fragment_add_contact.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider




class AddContact : Fragment(R.layout.fragment_add_contact) {

    private lateinit var database: ContactDataBase
    private lateinit var contactDao: ContactDao

    @Inject
    lateinit var factory: ViewModelProviderFactory

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
        val scope = CoroutineScope(Dispatchers.Main)
        database = ContactDataBase.getDataBase(requireContext(), scope)
        contactDao = database.ContactDao()

        viewModel = ViewModelProvider(this, factory)[ContactViewModel::class.java]

        view.create_contact.setOnClickListener {
            val contact = Contact(name = textInputEditText.text.toString(), number = textInputEditText2.text.toString(), address = textInputEditText3.text.toString())
            viewModel.saveContact(contact = contact)
        }
        return view
    }
    fun something(item: Contact){
        Log.i("Something", "username: ${item.name} number: ${item.number} address ${item.address}")
    }
}