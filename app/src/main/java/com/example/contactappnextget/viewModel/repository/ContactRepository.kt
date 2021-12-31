package com.example.contactappnextget.viewModel.repository

import androidx.lifecycle.LiveData
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.model.ContactDao
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao) {
    val contacts: LiveData<List<Contact>?> = contactDao.getAllContacts()

    fun insertContact(contact: Contact){
        contactDao.insert(contact = contact)
    }

    fun getAllContacts() : LiveData<List<Contact>?> = contactDao.getAllContacts()

    fun findContactByName(query: String): LiveData<List<Contact>> = contactDao.findContactByName(query)

    suspend fun updateContact(contact: Contact){
        contactDao.updateContact(contact = contact)
    }

    suspend fun deleteContact(contact: Contact){
        contactDao.delete(contact = contact)
    }

    fun findContact(contactId: Long) {
        contactDao.findContactById(contactId)
    }



//    suspend fun addsomething

//    suspend fun deleteAll(){}
}