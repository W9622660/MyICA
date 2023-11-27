package com.example.myica.data.api

import com.example.myica.data.api.model.Force
import retrofit2.http.GET

interface PoliceAPI {
    @GET(ApiConstants.FORCES)
    suspend fun getForce() : List<Force>
}