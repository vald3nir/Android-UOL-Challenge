package com.vald3nir.data.rest

import com.vald3nir.data.models.EventResponseBody
import com.vald3nir.data.models.ListEventsResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsService {

    @GET("events")
    suspend fun getListEvents(): Response<ListEventsResponseBody>

    @GET("events/{id}")
    suspend fun getEventById(@Path("id") id: String?): Response<EventResponseBody>


}