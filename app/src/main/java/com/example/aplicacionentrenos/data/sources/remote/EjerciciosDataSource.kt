package com.example.aplicacionentrenos.data.sources.remote

import com.example.aplicacionentrenos.data.sources.remote.retrofit.EjercicioService
import com.example.aplicacionentrenos.data.sources.remote.utils.BaseApiResponse
import javax.inject.Inject

class EjerciciosDataSource @Inject constructor(
    private val ejercicioService: EjercicioService
) : BaseApiResponse() {

    suspend fun getAll() = safeApiCall(apiCall = { ejercicioService.getAllEjercicios() })

    suspend fun getById(id: Int) = safeApiCall(apiCall = { ejercicioService.getById(id) })

    suspend fun getByName(value: String) =
        safeApiCall(apiCall = { ejercicioService.getByName(value) })
}