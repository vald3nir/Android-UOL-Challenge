package com.vald3nir.data.database

import android.content.Context
import androidx.room.Room
import com.vald3nir.data.models.Event

class DatabaseHandler(context: Context) {

    private val eventDao = Room.databaseBuilder(
        context, AppDatabase::class.java, "database.db"
    ).createFromAsset("database/database_embedded.db").build().EventDao()


    fun listEvents(): List<Event>? {
        return eventDao.getAll()
    }

    fun getEvent(eventID: String?): Event? {
        return eventDao.loadById(eventID)
    }
}