package com.example.aplicacionentrenos.data.repository

import com.example.aplicacionentrenos.data.sources.remote.EjerciciosDataSource
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.domain.model.ClienteDTO
import com.example.aplicacionentrenos.domain.model.EjercicioDTO
import com.example.aplicacionentrenos.domain.model.UserDTO
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class EjericiciosRepository @Inject constructor(
    private val ejerciciosDataSource: EjerciciosDataSource
) {

    fun getAll() : Flow<NetworkResult<List<EjercicioDTO>>> {
        return flow{
            emit(NetworkResult.Loading())
            emit(ejerciciosDataSource.getAll())
        }.flowOn(Dispatchers.IO)
    }

    fun getById(id : Int) : Flow<NetworkResult<EjercicioDTO>> {
        return flow{
            emit(NetworkResult.Loading())
            emit(ejerciciosDataSource.getById(id))
        }.flowOn(Dispatchers.IO)
    }




}