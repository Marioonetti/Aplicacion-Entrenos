package com.example.aplicacionentrenos.data.sources.remote

import com.example.aplicacionentrenos.data.sources.remote.retrofit.EntrenosService
import com.example.aplicacionentrenos.data.sources.remote.utils.BaseApiResponse
import com.example.aplicacionentrenos.domain.datamappers.toEntreno
import javax.inject.Inject

class EntrenoDataSource @Inject constructor(
    private val entrenosService: EntrenosService
) : BaseApiResponse() {

    suspend fun getAllDesc(id: Int) = safeApiCall(apiCall = {
        entrenosService.getAllEntrenosDesc(id)
    },
        transform = { list -> list.map { it.toEntreno() } })

    suspend fun getAllAsc(id: Int) = safeApiCall(apiCall = {
        entrenosService.getAllEntrenosAsc(id)
    },
        transform = { list -> list.map { it.toEntreno() } })

    suspend fun getById(id: Int) = safeApiCall(apiCall = {
        entrenosService.getEntrenoById(id)
    })


}