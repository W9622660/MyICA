package com.example.myica.data.repository

import com.example.myica.data.api.PoliceAPI
import com.example.myica.data.api.model.Force
import com.example.myica.data.api.model.Crime
import javax.inject.Inject

class  PoliceRepo @Inject constructor(
    private val policeAPI: PoliceAPI
){
    suspend fun getForces():List<Force>{
        return policeAPI.getForce()
    }
    suspend fun getAllCrimes():List<Crime>{
        return policeAPI.getAllCrimes("54.5680362", "-1.2417789")
    }
}