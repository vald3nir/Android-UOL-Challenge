package com.vald3nir.data.database

import androidx.room.Dao
import androidx.room.Query
import com.vald3nir.data.models.Event


@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun getAll(): List<Event>

    @Query("SELECT * FROM event WHERE event_id IN (:id)")
    fun loadById(id: Int): Event


}