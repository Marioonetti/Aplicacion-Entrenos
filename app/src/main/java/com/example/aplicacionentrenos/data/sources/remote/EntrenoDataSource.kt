package com.example.aplicacionentrenos.data.sources.remote

import com.example.aplicacionentrenos.data.sources.remote.retrofit.EntrenosService
import com.example.aplicacionentrenos.data.sources.remote.utils.BaseApiResponse
import javax.inject.Inject

class EntrenoDataSource @Inject constructor(
    private  val entrenosService: EntrenosService
) : BaseApiResponse(){

    suspend fun getAll() = safeApiCall(apiCall = {entrenosService.getAllEntrenos()})



}