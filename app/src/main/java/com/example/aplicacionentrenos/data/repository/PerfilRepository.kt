package com.example.aplicacionentrenos.data.repository

import com.example.aplicacionentrenos.data.sources.remote.ClienteDataSource
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class PerfilRepository @Inject constructor(
    private val clienteDataSource: ClienteDataSource
) {

    fun getById(id: Int): Flow<NetworkResult<ClienteDTO>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(clienteDataSource.getInfoById(id))
        }.flowOn(Dispatchers.IO)
    }


}