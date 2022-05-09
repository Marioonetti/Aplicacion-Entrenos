package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import retrofit2.Response
import retrofit2.http.GET

interface EntrenadorService {

    @GET(RestConstants.ENTRENADORES_PATH)
    suspend fun getAllEntrenadores() : Response<EntrenadorDTO>

}