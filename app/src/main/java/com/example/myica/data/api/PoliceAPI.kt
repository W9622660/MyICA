package com.example.myica.data.api

import com.example.myica.data.api.model.Force
import com.example.myica.data.api.model.Crime
import retrofit2.http.GET
import retrofit2.http.Query

interface PoliceAPI {
    @GET(ApiConstants.FORCES)
    suspend fun getForce() : List<Force>

    @GET(ApiConstants.SEARCH)
    suspend fun getAllCrimes(
        @Query("lat")
        lat: String,
        @Query("lng")
        lan: String
    ) : List<Crime>
}