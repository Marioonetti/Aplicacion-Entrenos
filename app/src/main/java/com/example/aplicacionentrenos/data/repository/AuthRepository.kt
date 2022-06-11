package com.example.aplicacionentrenos.data.repository

import com.example.aplicacionentrenos.data.sources.remote.AuthDataSource
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import com.example.aplicacionentrenos.domain.model.dto.UserDTO
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class AuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource
) {

    fun login(userDTO: UserDTO): Flow<NetworkResult<ClienteDTO>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(authDataSource.login(userDTO))
        }.flowOn(Dispatchers.IO)
    }

    fun registro(clienteDTO: ClienteDTO): Flow<NetworkResult<ClienteDTO>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(authDataSource.registro(clienteDTO))
        }.flowOn(Dispatchers.IO)
    }


}