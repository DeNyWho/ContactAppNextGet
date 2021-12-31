package com.example.contactappnextget.di

import android.app.Application
import com.example.contactappnextget.model.ContactDao
import com.example.contactappnextget.room.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun getAppDatabase(context: Application): ContactDatabase {
        return ContactDatabase.getAppDbInstance(context)
    }

    @Singleton
    @Provides
    fun appDao(appDatabase: ContactDatabase): ContactDao {
        return appDatabase.getAppDao()
    }
}