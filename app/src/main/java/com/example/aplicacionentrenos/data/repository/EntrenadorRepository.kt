package com.example.aplicacionentrenos.data.repository

import com.example.aplicacionentrenos.data.sources.remote.EntrenadorDataSource
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class EntrenadorRepository @Inject constructor(
    private val entrenadorDataSource: EntrenadorDataSource
) {


    fun getAll(): Flow<NetworkResult<List<Entrenador>>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(entrenadorDataSource.getAll())
        }.flowOn(Dispatchers.IO)
    }

    fun getById(id: Int): Flow<NetworkResult<Entrenador>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(entrenadorDataSource.getById(id))
        }.flowOn(Dispatchers.IO)
    }

    fun altaEnrenador(clienteDTO: ClienteDTO): Flow<NetworkResult<ClienteDTO>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(entrenadorDataSource.altaEntrenador(clienteDTO))
        }.flowOn(Dispatchers.IO)
    }



}