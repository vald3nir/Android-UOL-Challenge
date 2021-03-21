package com.vald3nir.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vald3nir.data.models.Event


@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun EventDao(): EventDao
}