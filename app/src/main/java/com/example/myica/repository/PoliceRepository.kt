package com.example.myica.repository

import com.example.myica.data.DataOrException
import com.example.myica.models.Force
import com.example.myica.models.ForceResponse
import com.example.myica.models.NewsResponse
import com.example.myica.network.PoliceApi
import javax.inject.Inject

class PoliceRepository @Inject constructor(private val api: PoliceApi) {

    suspend fun getForces(
    ):DataOrException<List<Force>,Boolean,Exception> {
        val response = try {
             api.getForces()
        }catch (e:Exception){
            return DataOrException(loading = false,exception = e)
        }
        println(response)
        return DataOrException(response,false)
    }

    suspend fun searchNews(searchQuery:String,page: Int = 1):DataOrException<NewsResponse,Boolean,Exception> {
        val response = try {
            api.searchNews(searchQuery = searchQuery, page = page)

        }catch (e:Exception){
            return DataOrException(loading = false,exception = e)
        }
        return DataOrException(response,false)

    }


}