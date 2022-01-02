package com.example.contactappnextget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.contactappnextget.fragments.ContactListTab
import com.example.contactappnextget.fragments.FavouriteListTab

class ContactListAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactListTab()
            1 -> FavouriteListTab()
            else -> {
                Fragment()
            }
        }
    }


}