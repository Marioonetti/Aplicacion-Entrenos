package com.example.aplicacionentrenos.data.sources.remote

import com.example.aplicacionentrenos.data.sources.remote.retrofit.EntrenadorService
import com.example.aplicacionentrenos.data.sources.remote.utils.BaseApiResponse
import com.example.aplicacionentrenos.domain.datamappers.toEntrenador
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import javax.inject.Inject

class EntrenadorDataSource @Inject constructor(
    private val entrenadorService: EntrenadorService
) : BaseApiResponse() {

    suspend fun getAll() = safeApiCall(
        apiCall = { entrenadorService.getAllEntrenadores() },
        transform = { it.map { entrenadorDto -> entrenadorDto.toEntrenador() } }
    )

    suspend fun getById(id: Int) = safeApiCall(
        apiCall = { entrenadorService.getEntrenadorById(id) },
        transform = { it.toEntrenador() }
    )

    suspend fun altaEntrenador(clienteDto: ClienteDTO) = safeApiCall(
        apiCall = { entrenadorService.altaEntrenador(clienteDto) }
    )

    suspend fun bajaEntrenador(clienteDto: ClienteDTO) = safeApiCall(
        apiCall = { entrenadorService.bajaEntrenador(clienteDto) }
    )

}