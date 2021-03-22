package com.vald3nir.data

import android.content.Context
import com.vald3nir.data.database.DatabaseHandler
import com.vald3nir.data.exceptions.InvalidEmailException
import com.vald3nir.data.exceptions.InvalidEventIDException
import com.vald3nir.data.exceptions.InvalidNameException
import com.vald3nir.data.models.Event
import com.vald3nir.data.rest.RestClient

class DataController(context: Context) {

    private val restClient = RestClient()
    private val database = DatabaseHandler(context)

    suspend fun listEvents(): List<Event>? {
        return try {
            restClient.listAllEvents()
        } catch (e: Exception) {
            e.printStackTrace()
            database.listEvents()
        }
    }

    suspend fun getEvent(eventID: String?): Event? {
        return try {
            restClient.getEvent(eventID)
        } catch (e: Exception) {
            e.printStackTrace()
            database.getEvent(eventID)
        }
    }

    @Throws(Exception::class)
    suspend fun makeCheckIn(eventId: String?, email: String?, name: String?) {
        if (email == null || email.isEmpty()) throw InvalidEmailException()
        if (name == null || name.isEmpty()) throw InvalidNameException()
        if (eventId == null || eventId.isEmpty()) throw InvalidEventIDException()
        restClient.makeCheckIn(eventId = eventId, email = email, name = name)
    }

}