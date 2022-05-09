package com.example.aplicacionentrenos.data.repository

import com.example.aplicacionentrenos.data.sources.remote.RemoteDataSource
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.domain.model.ClienteDTO
import com.example.aplicacionentrenos.domain.model.UserDTO
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class AuthRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){

    fun login(userDTO: UserDTO) : Flow<NetworkResult<ClienteDTO>>{
        return flow{
            emit(NetworkResult.Loading())
            emit(remoteDataSource.login(userDTO))
        }.flowOn(Dispatchers.IO)
    }

    fun registro(clienteDTO: ClienteDTO) : Flow<NetworkResult<ClienteDTO>>{
        return flow{
            emit(NetworkResult.Loading())
            emit(remoteDataSource.registro(clienteDTO))
        }.flowOn(Dispatchers.IO)
    }



}