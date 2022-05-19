package com.example.aplicacionentrenos.ui.screens.perfil

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.ui.screens.entrenamientos.detalles.EntrenoDetallesContract
import com.example.aplicacionentrenos.utils.UiEvents
import com.example.aplicacionentrenos.utils.UserCache
import kotlinx.coroutines.flow.collect


@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel = hiltViewModel()
) {
    val cliente = viewModel.perfilState.collectAsState().value.cliente
    val entrenador = viewModel.perfilState.collectAsState().value.entrenador

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {

        UserCache.id?.let{
            viewModel.handleEvent(PerfilContract.Eventos.GetEntrenadorById(it))
            viewModel.handleEvent(PerfilContract.Eventos.GetClienteByID(it))
        }

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.mensaje
                    )
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {}

}