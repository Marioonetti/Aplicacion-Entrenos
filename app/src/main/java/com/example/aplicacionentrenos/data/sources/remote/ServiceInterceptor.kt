package com.example.aplicacionentrenos.data.sources.remote

import com.example.aplicacionentrenos.utils.UserCache
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request : Request = chain.request()
        var requestResult : Request
        var response : Response


        if (!UserCache.username.isNullOrBlank() && !UserCache.username.isNullOrBlank()){
            if (!UserCache.token.isNullOrBlank()){
                requestResult = request.newBuilder()
                    .header("Authorization",
                        "Bearer ${UserCache.token}")
                    .build()
            }
            else{
//                Primera vez que pido token
                requestResult = request.newBuilder()
                    .header("Authorization",
                        Credentials.basic(UserCache.username, UserCache.password))
                    .build()
            }
        }
        else{
//            Para login o registro dejamos pasar la llamada sin cabecera
            requestResult = request.newBuilder().build()
        }

        response = chain.proceed(requestResult)

        response.header("Authorization")?.let{
            UserCache.token = it
        }

        if (!response.isSuccessful){
//            Miramos si ha expirado
            response.header("Expires")?.let {
                response.close()
                UserCache.token = ""
                requestResult = request.newBuilder()
                    .header("Authorization",
                        Credentials.basic(UserCache.username, UserCache.password))
                    .build()
                response = chain.proceed(requestResult)

                response.header("Authorization")?.let{
                    UserCache.token = it
                }

            }

        }
        return response
    }
}