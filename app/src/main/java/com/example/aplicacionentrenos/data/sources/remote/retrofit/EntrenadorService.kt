package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.dto.EntrenadorDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface EntrenadorService {

    @GET(RestConstants.ENTRENADORES_PATH)
    suspend fun getAllEntrenadores() : Response<List<EntrenadorDTO>>

    @GET(RestConstants.ENTRENADORES_PATH+RestConstants.ENTRENADOR_PATH_PARAM)
    suspend fun getEntrenadorById(@Path("id") id : Int) : Response<EntrenadorDTO>

    @PUT(RestConstants.ENTRENADORES_PATH+RestConstants.ENTRENADOR_PATH_PARAM)
    suspend fun altaEntrenador(@Body id : Int) : Response<EntrenadorDTO>
}