package com.example.contactappnextget.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao{
    @Query("SELECT * FROM contact_info ORDER BY contact_name ASC")
    fun getContacts(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM contact_info")
    fun getContact(): List<Contact>

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("DELETE FROM contact_info")
    suspend fun deleteAll()

}