package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.domain.model.EntrenoDTO
import retrofit2.Response
import retrofit2.http.GET

interface EntrenosService {

    @GET
    suspend fun getAllEntrenos() : Response<List<EntrenoDTO>>




}