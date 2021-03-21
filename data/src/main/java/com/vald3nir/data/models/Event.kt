package com.vald3nir.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
class Event(
    @PrimaryKey
    val uid: Int? = null,
    @ColumnInfo(name = "event_id") val eventID: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "price") val price: Float?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "latitude") val latitude: Float?,
    @ColumnInfo(name = "longitude") val longitude: Float?
) {}