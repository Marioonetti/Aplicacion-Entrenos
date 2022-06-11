package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import com.example.aplicacionentrenos.domain.model.dto.EntrenadorDTO
import com.example.aplicacionentrenos.utils.Constantes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface EntrenadorService {

    @GET(RestConstants.ENTRENADORES_PATH)
    suspend fun getAllEntrenadores(): Response<List<EntrenadorDTO>>

    @GET(RestConstants.ENTRENADORES_PATH + RestConstants.ENTRENADOR_PATH_PARAM)
    suspend fun getEntrenadorById(@Path(Constantes.ID) id: Int): Response<EntrenadorDTO>

    @PUT(RestConstants.CLIENTE_PATH + RestConstants.CLIENTE_ALTA_PATH)
    suspend fun altaEntrenador(@Body clienteDTO: ClienteDTO): Response<ClienteDTO>

    @PUT(RestConstants.CLIENTE_PATH + RestConstants.CLIENTE_BAJA_PATH)
    suspend fun bajaEntrenador(@Body clienteDTO: ClienteDTO): Response<ClienteDTO>
}