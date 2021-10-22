package com.example.contactappnextget.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactappnextget.R
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.viewModel.ContactViewModel
import kotlinx.android.synthetic.main.fragment_contact_list_tab.*

class ContactListTab : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val contacts = contactViewModel.contact.observeAsState(listOf<Contact>()).value

//        linearLayoutManager = LinearLayoutManager(requireContext())
//        recyclerview.layoutManager = linearLayoutManager

        return inflater.inflate(R.layout.fragment_contact_list_tab, container, false)
//        model.
    }

    companion object {
//        fun newInstance(): AddContact = AddContact()
    }
}