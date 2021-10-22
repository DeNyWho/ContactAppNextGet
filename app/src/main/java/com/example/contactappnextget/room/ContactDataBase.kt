package com.example.contactappnextget.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.model.ContactDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDataBase: RoomDatabase() {

    abstract fun ContactDao(): ContactDao

    companion object {
        private var INSTANCE: ContactDataBase? = null

        fun getDataBase(context: Context, scope: CoroutineScope): ContactDataBase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDataBase::class.java,
                        "contact_database"
                    ).addCallback(ContactDataBaseCallback(scope = scope)).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private class ContactDataBaseCallback(val scope: CoroutineScope): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch { populateDatabase(database.ContactDao()) }
            }
        }

        suspend fun populateDatabase(ContactDao: ContactDao){
            ContactDao.deleteAll()
        }
    }
}