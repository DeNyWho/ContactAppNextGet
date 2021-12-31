package com.example.contactappnextget.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactappnextget.R
import com.example.contactappnextget.adapter.ContactAdapter
import com.example.contactappnextget.viewModel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_contact_list_tab.*

@AndroidEntryPoint
class ContactListTab : Fragment() {
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list_tab, container, false)
        recyclerView = view.findViewById(R.id.ContactList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        val adapter = ContactAdapter()
        recyclerView.adapter = adapter

        contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        contactViewModel.getAllContacts.observe(viewLifecycleOwner) { contacts ->
            if (contacts != null) {
                adapter.setContacts(contacts)
            }
        }

        return view
    }
}