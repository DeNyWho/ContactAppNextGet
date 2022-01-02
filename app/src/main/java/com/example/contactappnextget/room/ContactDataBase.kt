package com.example.contactappnextget.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.model.ContactDao

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun getAppDao(): ContactDao

    companion object {
        @Volatile
        private var DB_INSTANCE: ContactDatabase? = null

        fun getAppDbInstance(context: Context): ContactDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext, ContactDatabase::class.java, "MYDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }

    }
}