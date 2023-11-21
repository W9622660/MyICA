package uk.ac.tees.w9622660.ukpolicenewsapp.data.remote

import uk.ac.tees.w9622660.ukpolicenewsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page :Int,
        @Query("sources") sources :String,
        @Query("apiKey") apiKey : String,
        @Query("pageSize") pageSize : Int,
    ) : NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery : String,
        @Query("page") page :Int,
        @Query("sources") sources :String,
        @Query("apiKey") apiKey : String,
        @Query("pageSize") pageSize : Int,
    ) : NewsResponse
}