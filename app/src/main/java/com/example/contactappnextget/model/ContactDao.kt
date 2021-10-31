package com.example.contactappnextget.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao{
    @Insert
    fun insert(contact: Contact): Long

    @Query("SELECT * FROM contact_info ORDER BY contact_name ASC")
    fun getAllContacts(): LiveData<List<Contact>?>

    @Query("SELECT * FROM contact_info")
    fun getContact(): List<Contact>

    @Query("SELECT * FROM contact_info WHERE contact_name LIKE '%' || :query || '%'")
    fun findContactByName(query: String): LiveData<List<Contact>>

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("DELETE FROM contact_info")
    suspend fun deleteAll()

}