package com.example.contactappnextget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.contactappnextget.fragments.ContactListTab
import com.example.contactappnextget.fragments.Contactlist
import com.example.contactappnextget.fragments.FavouriteListTab

class FragmentSlidePagerAdapter(fa: Contactlist): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0){ ContactListTab()}
        else FavouriteListTab()
    }
}