package com.example.aplicacionentrenos.ui.screens.entrenamientos.detalles

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.dto.SerieDTO
import com.example.aplicacionentrenos.ui.theme.primaryLightColor
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect
import kotlin.streams.toList


@Composable
fun EntrenoDetallesScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    id: Int?,
    viewModel: EntrenoDetallesViewModel = hiltViewModel()
) {
    val entreno = viewModel.entreno.collectAsState().value.entreno

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {

        id?.let {
            viewModel.handleEvent(EntrenoDetallesContract.Eventos.GetEntrenoById(id))
        }
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
    ) {}

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        entreno?.let {
            val listaEmpujes =
                entreno.series.stream().filter { it.enfoque.lowercase() == Constantes.EMPUJES }
                    .toList()
            val listaTracciones =
                entreno.series.stream().filter { it.enfoque.lowercase() == Constantes.TRACCIONES }
                    .toList()
            val listaPierna =
                entreno.series.stream().filter { it.enfoque.lowercase() == Constantes.PIERNA }
                    .toList()


            PintaEnfoque(nombre = Constantes.EMPUJES.uppercase())
            PintaSeries(listaSerie = listaEmpujes, viewModel = viewModel)

            PintaEnfoque(nombre = Constantes.TRACCIONES.uppercase())
            PintaSeries(listaSerie = listaTracciones, viewModel = viewModel)

            PintaEnfoque(nombre = Constantes.PIERNA.uppercase())
            PintaSeries(listaSerie = listaPierna, viewModel = viewModel)


        }

    }


}

@Composable
private fun PintaSeries(
    listaSerie: List<SerieDTO>,
    viewModel: EntrenoDetallesViewModel,
) {

    if (listaSerie.isNotEmpty()) {
        listaSerie.forEach {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clip(RectangleShape)
                        .border(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
                        .clickable { viewModel.handleEvent(EntrenoDetallesContract.Eventos.IrDetalleEjercicio(it.ejercicio.id)) },
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = it.ejercicio.nombre, style = MaterialTheme.typography.h6)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RectangleShape)
                            .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))

                    ) {
                        Text(text = it.seriesRepeticiones, style = MaterialTheme.typography.h6)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RectangleShape)
                            .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))

                    ) {
                        Text(text = "Rir ${it.rir}", style = MaterialTheme.typography.h6)
                    }
                }

            }
        }
    }

}

@Composable
private fun PintaEnfoque(
    nombre: String
) {

    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .background(primaryLightColor),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = nombre, style = MaterialTheme.typography.h1)
    }


}