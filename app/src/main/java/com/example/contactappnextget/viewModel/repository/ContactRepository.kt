package com.example.contactappnextget.viewModel.repository

import androidx.lifecycle.LiveData
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.model.ContactDao
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao) {

    fun insertContact(contact: Contact) {
        contactDao.insert(contact = contact)
    }

    fun getAllContacts(): LiveData<List<Contact>?> = contactDao.getAllContacts()

    fun deleteContact(contact: Contact) {
        contactDao.delete(contact = contact)
    }

    fun updateContact(contact: Contact) {
        contactDao.updateContact(contact)
    }

}