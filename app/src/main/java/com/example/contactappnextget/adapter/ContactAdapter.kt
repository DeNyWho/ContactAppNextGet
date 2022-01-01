package com.example.contactappnextget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.contactappnextget.R
import com.example.contactappnextget.fragments.ContactListDirections
import com.example.contactappnextget.model.Contact
import java.io.File


class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {
    private var contacts: List<Contact> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_contacts, parent, false)

        return ContactHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val currentContact: Contact = contacts[position]
        holder.textViewName.text = currentContact.getName()
        holder.imageViewName.load(File(currentContact.getImage())){
            crossfade(true)
            crossfade(1000)
            transformations(CircleCropTransformation())
        }
        holder.itemView.setOnClickListener {
            val action = ContactListDirections.actionContactListToDetailing(currentContact.getName(), currentContact.getAddress(), currentContact.getMobile())
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    inner class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.tv_name)
        val imageViewName: ImageView = itemView.findViewById(R.id.iv_profile)
    }
}