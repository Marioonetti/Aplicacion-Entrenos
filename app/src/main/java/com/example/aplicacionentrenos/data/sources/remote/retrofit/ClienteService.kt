package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import com.example.aplicacionentrenos.utils.Constantes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClienteService {


    @GET(RestConstants.CLIENTE_PATH + RestConstants.CLIENTE_PATH_PARAM)
    suspend fun getInfoById(@Path(Constantes.ID) id: Int): Response<ClienteDTO>

}