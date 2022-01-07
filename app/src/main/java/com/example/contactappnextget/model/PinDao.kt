package com.example.contactappnextget.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PinDao {
    @Insert
    fun insertPin(pin: Pin): Long

    @Query("SELECT * FROM pin_info ORDER BY pin_number ASC")
    fun getAllPin(): LiveData<List<Pin>?>

    @Update
    fun updatePin(pin: Pin)

    @Delete
    fun deletePin(pin: Pin)

}