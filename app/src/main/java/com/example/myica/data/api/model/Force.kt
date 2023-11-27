package com.example.myica.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Force(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String?
)