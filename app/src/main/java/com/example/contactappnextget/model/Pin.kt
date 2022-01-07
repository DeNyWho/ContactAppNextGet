package com.example.contactappnextget.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pin_info")
data class Pin(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pin_id") val id: Int = 0,
    @ColumnInfo(name = "pin_number") val number: String
) : Parcelable {

    fun getPin(): String {
        return number
    }

    fun getPinId(): Int {
        return id
    }
}