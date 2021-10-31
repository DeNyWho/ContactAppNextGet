package com.example.contactappnextget.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactappnextget.model.Contact

class ContactsAdapter(val context: Context, val contactsClickListener: ContactsClickListener): ListAdapter<Contact, RecyclerView.ViewHolder>(ContactListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactViewHolder()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}


class ContactViewHolder(private val binding: ListItemContactBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(ContactListener: ContactsClickListener, item: Contact){
        binding.apply{
            contactClickListener = contactListener
            contact = item
            executePendingBindings()
        }
    }

}


private class ContactListDiffCallback: DiffUtil.ItemCallback<Contact>(){
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}

class ContactsClickListener(val clickListener: (contact: Contact) -> Unit){
    fun onClick(contact: Contact) = clickListener(contact)
}