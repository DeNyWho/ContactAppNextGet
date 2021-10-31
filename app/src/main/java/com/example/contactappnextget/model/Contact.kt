package com.example.contactappnextget.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "contact_info")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_id") val id: Int = 0,
    @ColumnInfo(name = "contact_name") val name: String,
    @ColumnInfo(name = "contact_number") val number: String,
    @ColumnInfo(name = "contact_address") val address: String,
//    @ColumnInfo(name = "contact_image") val image: Int,
) : Parcelable