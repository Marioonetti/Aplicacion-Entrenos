package com.example.aplicacionentrenos.data.sources.remote.utils

import com.example.aplicacionentrenos.domain.model.dto.ApiError
import com.google.gson.Gson
import retrofit2.Response


abstract class BaseApiResponse {


    suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<R>,
        transform: (R) -> T
    ): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(transform(body))
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        var result : NetworkResult<T> = NetworkResult.Empty()
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    result =  NetworkResult.Success(body)
                }
            }
            else{
                response.errorBody()?.let { errorBody ->
                    val apiError : ApiError = Gson().fromJson(errorBody.string(), ApiError::class.java)
                    result = NetworkResult.Error(apiError.mensaje)
                }
            }
        } catch (e: Exception) {
            result = NetworkResult.Error(e.message ?: e.toString())
        }
        return result
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed: $errorMessage")


}