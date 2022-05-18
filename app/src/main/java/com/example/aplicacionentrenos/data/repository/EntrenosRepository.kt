package com.example.aplicacionentrenos.data.repository

import com.example.aplicacionentrenos.data.sources.remote.EntrenoDataSource
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.domain.model.bo.Entreno
import com.example.aplicacionentrenos.domain.model.dto.EntrenoDTO
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class EntrenosRepository @Inject constructor(
    private val entrenoDataSource: EntrenoDataSource
){


    fun getAllDesc(id: Int): Flow<NetworkResult<List<Entreno>>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(entrenoDataSource.getAllDesc(id))
        }.flowOn(Dispatchers.IO)
    }

    fun getAllAsc(id: Int): Flow<NetworkResult<List<Entreno>>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(entrenoDataSource.getAllDesc(id))
        }.flowOn(Dispatchers.IO)
    }

    fun getById(id: Int): Flow<NetworkResult<EntrenoDTO>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(entrenoDataSource.getById(id))
        }.flowOn(Dispatchers.IO)
    }




}