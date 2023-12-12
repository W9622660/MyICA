package com.example.myica.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Crime (
  @Json(name = "id")
  val id: String,
  @Json(name = "category")
  val category: String?,
  @Json(name = "location_type")
  val location_type: String,
  @Json(name = "location")
  val location: Location?,
  @Json(name = "context")
  val context: String,
  @Json(name = "outcome_status")
  val outcome_status: Any?,
  @Json(name = "persistent_id")
  val persistent_id: String?,
  @Json(name = "location_subtype")
  val location_subtype: String,
  @Json(name = "month")
  val month: String?

)