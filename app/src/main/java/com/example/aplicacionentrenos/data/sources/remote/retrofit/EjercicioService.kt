package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.dto.EjercicioDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EjercicioService {


    @GET(RestConstants.EJERCICIOS_PATH)
    suspend fun getAllEjercicios() : Response<List<EjercicioDTO>>

    @GET(RestConstants.EJERCICIOS_PATH + RestConstants.EJERCICIO_PATH_PARAM)
    suspend fun getById(@Path("id") id : Int) : Response<EjercicioDTO>

    @GET(RestConstants.EJERCICIOS_PATH + RestConstants.EJERCICIO_BY_NAME_PATH)
    suspend fun getByName(@Query("value") value : String) : Response<List<EjercicioDTO>>


}