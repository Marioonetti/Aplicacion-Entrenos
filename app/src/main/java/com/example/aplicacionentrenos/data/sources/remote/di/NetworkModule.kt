package com.example.aplicacionentrenos.data.sources.remote.di

import com.example.aplicacionentrenos.data.sources.remote.retrofit.*
import com.example.aplicacionentrenos.data.sources.remote.utils.RestConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @ServiceInterceptorAnottation
    fun provideInterceptor(): Interceptor = ServiceInterceptor()


    @Singleton
    @Provides
    fun provideHttpClient(
        @ServiceInterceptorAnottation
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RestConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideEjercicioService(retrofit: Retrofit): EjercicioService =
        retrofit.create(EjercicioService::class.java)

    @Singleton
    @Provides
    fun provideEntrenadorService(retrofit: Retrofit): EntrenadorService =
        retrofit.create(EntrenadorService::class.java)

    @Singleton
    @Provides
    fun provideEntrenosService(retrofit: Retrofit): EntrenosService =
        retrofit.create(EntrenosService::class.java)

    @Singleton
    @Provides
    fun provideClienteService(retrofit: Retrofit): ClienteService =
        retrofit.create(ClienteService::class.java)


}