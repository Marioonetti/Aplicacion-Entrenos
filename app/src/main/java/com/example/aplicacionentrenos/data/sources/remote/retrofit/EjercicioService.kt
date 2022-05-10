package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.ClienteDTO
import com.example.aplicacionentrenos.domain.model.EjercicioDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface EjercicioService {


    @GET(RestConstants.EJERCICIOS_PATH)
    suspend fun getAllEjercicios() : Response<List<EjercicioDTO>>

    @GET(RestConstants.EJERCICIOS_PATH + RestConstants.EJERCICIO_PATH_PARAM)
    suspend fun getById(@Path("id") id : Int) : Response<EjercicioDTO>


}