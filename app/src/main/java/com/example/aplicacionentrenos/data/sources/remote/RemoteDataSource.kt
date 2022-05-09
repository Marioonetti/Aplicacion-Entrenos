package com.example.aplicacionentrenos.data.sources.remote

import com.example.aplicacionentrenos.data.sources.remote.utils.BaseApiResponse
import com.example.aplicacionentrenos.data.sources.remote.retrofit.AuthService
import com.example.aplicacionentrenos.domain.model.ClienteDTO
import com.example.aplicacionentrenos.domain.model.UserDTO
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val authService: AuthService
) : BaseApiResponse(){

    suspend fun login(userDTO: UserDTO) = safeApiCall(apiCall = {authService.login(userDTO)})

    suspend fun registro(clienteDTO: ClienteDTO) = safeApiCall(apiCall = {authService.registro(clienteDTO)})


}