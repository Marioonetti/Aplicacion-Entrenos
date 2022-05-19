package com.example.aplicacionentrenos.data.sources.remote

import com.example.aplicacionentrenos.data.sources.remote.retrofit.ClienteService
import com.example.aplicacionentrenos.data.sources.remote.utils.BaseApiResponse
import javax.inject.Inject

class ClienteDataSource @Inject constructor(
    private val clienteService: ClienteService
) : BaseApiResponse() {

    suspend fun getInfoById(id :Int) = safeApiCall(apiCall = {clienteService.getInfoById(id)})


}