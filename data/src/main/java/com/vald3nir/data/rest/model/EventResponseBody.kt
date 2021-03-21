package com.vald3nir.data.rest.model

data class EventResponseBody(
    val id: String?,
    val title: String?,
    val description: String?,
    val price: Double?,
    val date: Int?,
    val image: String?,
    val latitude: Double?,
    val longitude: Double?,
)