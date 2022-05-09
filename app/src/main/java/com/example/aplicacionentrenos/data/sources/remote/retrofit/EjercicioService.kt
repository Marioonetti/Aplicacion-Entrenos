package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.ClienteDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface EjercicioService {


    @GET(RestConstants.EJERCICIOS_PATH)
    suspend fun getAllEjercicios(@Body clienteDTO: ClienteDTO) : Response<ClienteDTO>


}