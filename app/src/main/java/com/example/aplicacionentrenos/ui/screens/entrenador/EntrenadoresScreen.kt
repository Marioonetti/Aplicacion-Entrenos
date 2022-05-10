package com.example.aplicacionentrenos.ui.screens.entrenador

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect

@Composable
fun EntrenadoresScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: EntrenadoresViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.handleEvent(EntrenadoresContract.Eventos.GetAll)
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

    }

    Text(text = "Pantalla entrenadores")

}