package com.example.contactappnextget.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.viewModel.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class ContactViewModel(private val repository: ContactRepository): ViewModel() {

    val contacts: LiveData<List<Contact>?> = repository.contacts

    val getAllContacts = repository.getAllContacts()

    fun saveContact(contact: Contact){
        insertContact(contact)
    }

    private fun insertContact(contact: Contact){
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }
}

abstract class ContactViewModelFactory(val repository: ContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECK_CAST")
            return ContactViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}