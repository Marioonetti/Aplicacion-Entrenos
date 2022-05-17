package com.example.aplicacionentrenos.ui.screens.principal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.domain.model.dto.EntrenoDTO
import com.example.aplicacionentrenos.ui.screens.ejercicios.general.EjerciciosContract
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect

@Composable
fun PrincipalScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    navController: NavHostController,
    viewModel: EntrenamientosViewModel = hiltViewModel()
) {

    val listaEntrenos = viewModel.entrenos.collectAsState().value.entrenamientos

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.handleEvent(EjerciciosContract.Eventos.GetAll)
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.mensaje
                    )
                }
                is UiEvents.Navigate -> onNavigate(event)
                else -> Unit
            }
        }


    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (listaEntrenos.isEmpty()) {

            } else {
                LazyColumn {
                    items(listaEntrenos) { entreno ->
                        EntrenoItem(entreno, viewModel::handleEvent)
                    }

                }
            }
            LoadProgressBar(viewModel.loading)
        }
    }
}

@Composable
fun EntrenoItem(
    entreno: EntrenoDTO,
    handleEvent: (EjerciciosContract.Eventos) -> Unit
) {






}