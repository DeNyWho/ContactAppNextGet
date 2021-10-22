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

    val contact: LiveData<List<Contact>> = repository.contacts

    fun insertContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO){
        repository.insertContact(contact = contact)
    }
}

class ContactViewModelFactory(val repository: ContactRepository): ViewModelProvider.Factory{
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)){
            @Suppress("UNCHECK_CAST")
            return ContactViewModel(repository = repository) as T
        }
        throw IllegalStateException("Unknown viewModel Class")
    }
}