package com.example.contactappnextget.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.contactappnextget.R
import com.example.contactappnextget.adapter.FragmentSlidePagerAdapter
import kotlinx.android.synthetic.main.fragment_contactlist.*


class Contactlist : Fragment() {

    private lateinit var viewPager: ViewPager2

    interface Callbacks {
        fun onContactlist()
    }

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contactlist, container, false)
        viewPager = view.findViewById(R.id.pager)


        val pagerAdapter = FragmentSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
        // Inflate the layout for this fragment
        return view
    }

    override fun onStart() {
        super.onStart()
        addContact.setOnClickListener {
            callbacks?.onContactlist()
        }
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance(): Contactlist = Contactlist()
    }
}