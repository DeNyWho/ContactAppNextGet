package com.example.contactappnextget.viewModel.repository

import androidx.lifecycle.LiveData
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.model.ContactDao

class ContactRepository(private val contactDao: ContactDao) {
    val contacts: LiveData<List<Contact>> = contactDao.getContacts()

    suspend fun insertContact(contact: Contact){
        contactDao.insert(contact = contact)
    }

    suspend fun updateContact(contact: Contact){
        contactDao.updateContact(contact = contact)
    }

    suspend fun deleteContact(contact: Contact){
        contactDao.delete(contact = contact)
    }

//    suspend fun deleteAll(){}
}