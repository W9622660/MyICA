package com.example.myica.models

data class ForceResponse (

  val forces: List<Force>,
  val status: String,
  val totalResults: Int
)