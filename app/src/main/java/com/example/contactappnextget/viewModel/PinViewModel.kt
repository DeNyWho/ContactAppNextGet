package com.example.contactappnextget.viewModel

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.contactappnextget.model.Pin
//import com.example.contactappnextget.viewModel.repository.PinRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject

//@HiltViewModel
//class PinViewModel @Inject constructor(private val repository: PinRepository) : ViewModel() {
//
//    val getAllContacts = repository.getAllPin()
//
//    fun insertPin(pin: Pin) {
//        viewModelScope.launch {
//            repository.insertPin(pin = pin)
//        }
//    }
//
//    fun updatePin(pin: Pin) {
//        viewModelScope.launch {
//            repository.updatePin(pin = pin)
//        }
//    }
//
//    fun deletePin(pin: Pin) {
//        viewModelScope.launch {
//            repository.deletePin(pin = pin)
//        }
//    }
//}