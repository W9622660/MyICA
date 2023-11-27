package com.example.myica.models

import com.google.gson.annotations.SerializedName


data class Force (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null

)