package com.example.contactappnextget.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.contactappnextget.R
import com.example.contactappnextget.adapter.AddPagerAdapter
import com.example.contactappnextget.model.Contact
import kotlinx.android.synthetic.main.fragment_add_contact.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class AddContact : Fragment(R.layout.fragment_add_contact) {


    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    lateinit var adapters: AddPagerAdapter
    lateinit var list:MutableList<Int>

    private fun images(){
        list = mutableListOf()
        list.add(R.drawable.man_1)
        list.add(R.drawable.plusimage)
    }

    interface Callbacks {
        fun onAddContact()
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
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)

        images()

        adapters = AddPagerAdapter(this)
        adapters.setContentList(list)

        relativeLayout2.adapter = adapters

        val name = view.findViewById<EditText>(R.id.textField)
        val surname = view.findViewById<EditText>(R.id.textField2)
        val mobile = view.findViewById<EditText>(R.id.textField3)
        val address = view.findViewById<EditText>(R.id.textField4)
        save.setOnClickListener { Contact(name = name.text.toString(), surname = surname.text.toString(), number = mobile.text.toString(), address = address.text.toString() )}

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance(): AddContact = AddContact()
    }
}