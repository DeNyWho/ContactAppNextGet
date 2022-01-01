package com.example.contactappnextget.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.contactappnextget.R
import com.example.contactappnextget.adapter.ContactListAdapter
import com.example.contactappnextget.viewModel.ContactViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_contactlist.view.*

@AndroidEntryPoint
class ContactList : Fragment() {

    private lateinit var viewModel: ContactViewModel


    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Contacts"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contactlist, container, false)
        view.create_contact.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_addContact)
        }

       viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

//      click on contact

//      TabLayout + viewPager
        val tabLayout=view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager=view.findViewById<ViewPager2>(R.id.viewPager)

        val adapter = ContactListAdapter((activity as AppCompatActivity).supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> tab.text = "MY CONTACTS"
                1 -> tab.text = "FAVOURITE"
            }
        }.attach()

        return view
    }
}