package com.example.aplicacionentrenos.data.sources.remote.retrofit

import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import com.example.aplicacionentrenos.utils.UserCache
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var requestResult: Request
        var response: Response

        if (!UserCache.username.isNullOrBlank() && !UserCache.username.isNullOrBlank()) {
            if (!UserCache.token.isNullOrBlank()) {
                requestResult = request.newBuilder()
                    .header(
                        RestConstants.HEADER_AUTH,
                        "Bearer ${UserCache.token}"
                    )
                    .build()
            } else {

                requestResult = request.newBuilder()
                    .header(
                        RestConstants.HEADER_AUTH,
                        Credentials.basic(UserCache.username, UserCache.password)
                    )
                    .build()
            }
        } else {

            requestResult = request.newBuilder().build()
        }

        response = chain.proceed(requestResult)

        response.header(RestConstants.HEADER_AUTH)?.let {
            UserCache.token = it
        }

        if (!response.isSuccessful) {

            response.header(RestConstants.HEADER_EXPIRES)?.let {
                response.close()
                UserCache.token = ""
                requestResult = request.newBuilder()
                    .header(
                        RestConstants.HEADER_AUTH,
                        Credentials.basic(UserCache.username, UserCache.password)
                    )
                    .build()
                response = chain.proceed(requestResult)

                response.header(RestConstants.HEADER_AUTH)?.let {
                    UserCache.token = it
                }

            }

        }
        return response
    }
}