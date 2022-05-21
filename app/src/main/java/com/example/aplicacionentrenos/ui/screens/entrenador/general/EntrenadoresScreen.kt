package com.example.aplicacionentrenos.ui.screens.entrenador.general

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.ui.screens.shared.ImagenPreview
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.ui.theme.GrisFondo
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect

@Composable
fun EntrenadoresScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: EntrenadoresViewModel = hiltViewModel()
) {

    val listaEntrenadores = viewModel.entrenadores.collectAsState().value.entrenadores

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
        Box(modifier = Modifier.padding(it)) {
            LazyColumn {
                items(listaEntrenadores) { entrenador ->
                    EntrenadorItem(entrenador, viewModel::handleEvent)
                }

            }

            LoadProgressBar(viewModel.loading)
        }
    }


}

@Composable
private fun EntrenadorItem(
    entrenador: Entrenador,
    onEvent: (EntrenadoresContract.Eventos) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height(250.dp)
            .clickable {
                onEvent(EntrenadoresContract.Eventos.NavToInformacion(entrenador.id))
            },
        elevation = 5.dp
    ) {
        Row(modifier = Modifier.background(color = GrisFondo)) {
            ImagenPreview(entrenador.imagen)
            OverView(entrenador)
        }

    }


}


@Composable
private fun OverView(entrenador: Entrenador) {

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = entrenador.nombre,
                style = MaterialTheme.typography.h4
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = entrenador.apellidos,
                style = MaterialTheme.typography.h6
            )

            Row(
                modifier = Modifier
                    .wrapContentSize(Alignment.BottomCenter)
            ) {
                Text(
                    text = "${entrenador.edad} Años",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
            }

        }


    }


}
