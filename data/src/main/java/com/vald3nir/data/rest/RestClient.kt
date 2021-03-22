package com.vald3nir.data.rest

import com.vald3nir.data.exceptions.RequestHttpException
import com.vald3nir.data.mapper.toEvent
import com.vald3nir.data.mapper.toListEvents
import com.vald3nir.data.models.CheckInRequestBody
import com.vald3nir.data.models.Event
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RestClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io/api/")
        .build()

    @Throws(Exception::class)
    suspend fun makeCheckIn(eventId: String?, email: String?, name: String?) {
        val service: CheckInService = retrofit.create(CheckInService::class.java)
        val response = service.makeCheckIn(
            CheckInRequestBody(
                eventId = eventId,
                email = email,
                name = name
            )
        )
        if (response.code() != 200) {
            throw RequestHttpException(response.code())
        }
    }

    @Throws(Exception::class)
    suspend fun listAllEvents(): List<Event>? {
        val service: EventsService = retrofit.create(EventsService::class.java)
        val response = service.getListEvents()
        val body = response.body()
        if (response.code() == 200) {
            return body?.toListEvents()
        } else {
            throw RequestHttpException(response.code())
        }
    }

    @Throws(Exception::class)
    suspend fun getEvent(id: String?): Event? {
        val service: EventsService = retrofit.create(EventsService::class.java)
        val response = service.getEventById(id)
        val body = response.body()
        if (response.code() == 200) {
            return body?.toEvent()
        } else {
            throw RequestHttpException(response.code())
        }
    }
}