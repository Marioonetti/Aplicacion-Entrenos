package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.dto.EntrenoDTO
import retrofit2.Response
import retrofit2.http.GET

interface EntrenosService {

    @GET(RestConstants.EJERCICIOS_PATH + RestConstants.ENTRENAMIENTOS_PATH)
    suspend fun getAllEntrenos() : Response<List<EntrenoDTO>>




}