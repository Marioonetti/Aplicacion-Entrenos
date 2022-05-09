package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.domain.model.ClienteDTO
import com.example.aplicacionentrenos.domain.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(RestConstants.LOGIN_PATH)
    suspend fun login(@Body userDTO: UserDTO) : Response<ClienteDTO>

    @POST(RestConstants.REGISTER_PATH)
    suspend fun registro(@Body clienteDTO: ClienteDTO) : Response<ClienteDTO>


}