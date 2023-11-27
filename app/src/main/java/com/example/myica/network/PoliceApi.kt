package com.example.myica.network

import com.example.myica.models.Force
import com.example.myica.models.NewsResponse
import com.example.myica.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PoliceApi {

    @GET("/api/forces")
    suspend fun getForces(
    ): List<Force>

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("q") searchQuery : String = "",
        @Query("pageSize") pageSize : Int = 20
    ): NewsResponse
}