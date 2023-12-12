package com.example.myica.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location (

  @Json(name = "latitude")
  val latitude: String,
  @Json(name = "street")
  val street: Street?,
  @Json(name = "longitude")
  val longitude: String
)