package com.example.aplicacionentrenos.data.repository

import com.example.aplicacionentrenos.data.sources.remote.ClienteDataSource
import com.example.aplicacionentrenos.data.sources.remote.EntrenadorDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class PerfilRepository  @Inject constructor(
    private val clienteDataSource: ClienteDataSource,
    private val entrenadorDataSource: EntrenadorDataSource
){




}