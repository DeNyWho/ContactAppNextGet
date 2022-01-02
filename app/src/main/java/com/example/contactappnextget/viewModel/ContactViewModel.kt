package com.example.contactappnextget.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.viewModel.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {

    val getAllContacts = repository.getAllContacts()

//    fun findContactByName(query: String): LiveData<List<Contact>> {
//        return repository.findContactByName(query)
//    }

    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
}