package com.example.contactappnextget.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
//
//@HiltAndroidApp
//class Contact: Application(){
//    private val applicationScope = CoroutineScope(SupervisorJob())
//    private val database by lazy { ContactDatabase.getDatabase(this, applicationScope) }
//    val repository by lazy { Repository(database.contactDao()) }
//}