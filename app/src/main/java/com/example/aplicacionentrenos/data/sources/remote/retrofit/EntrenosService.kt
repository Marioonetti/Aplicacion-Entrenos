package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.dto.EntrenoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EntrenosService {

    @GET(RestConstants.ENTRENAMIENTOS_PATH + RestConstants.ENTRENAMIENTOS_DESC_PATH)
    suspend fun getAllEntrenosDesc(@Query(RestConstants.ID_CLIENTE_PARAM) id: Int): Response<List<EntrenoDTO>>

    @GET(RestConstants.ENTRENAMIENTOS_PATH + RestConstants.ENTRENAMIENTOS_ASC_PATH)
    suspend fun getAllEntrenosAsc(@Query(RestConstants.ID_CLIENTE_PARAM) id: Int): Response<List<EntrenoDTO>>

    @GET(RestConstants.ENTRENAMIENTOS_PATH + RestConstants.ENTRENAMIENTO_PATH_PARAM)
    suspend fun getEntrenoById(@Path(RestConstants.ID_ENTRENAMIENTO_PARAM) id: Int): Response<EntrenoDTO>


}