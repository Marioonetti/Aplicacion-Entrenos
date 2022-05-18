package com.example.aplicacionentrenos.ui.screens.entrenamientos.detalles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.dto.EntrenoDTO
import com.example.aplicacionentrenos.domain.model.dto.SerieDTO
import com.example.aplicacionentrenos.ui.screens.entrenador.detalles.DetallesEntrenadorContract
import com.example.aplicacionentrenos.ui.theme.primaryLightColor
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect
import java.util.*
import kotlin.streams.toList


@Composable
fun EntrenoDetallesScreen(
    id : Int?,
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
            .fillMaxHeight()
            .width(300.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        entreno?.let {
            val listaEmpujes = entreno.series.stream().filter { it.enfoque.lowercase() == Constantes.EMPUJES }.toList()
            val listaTracciones = entreno.series.stream().filter { it.enfoque.lowercase() == Constantes.TRACCIONES }.toList()
            val listaPierna = entreno.series.stream().filter { it.enfoque.lowercase() == Constantes.PIERNA }.toList()


            PintaEnfoque(nombre = Constantes.EMPUJES.uppercase())
                PintaSeries(listaSerie = listaEmpujes)

            PintaEnfoque(nombre = Constantes.TRACCIONES.uppercase())
                PintaSeries(listaSerie = listaTracciones)

            PintaEnfoque(nombre = Constantes.PIERNA.uppercase())
                PintaSeries(listaSerie = listaPierna)




        }

    }



}

@Composable
private fun PintaSeries(
    listaSerie : List<SerieDTO>
){

    if (listaSerie.isNotEmpty()){
        listaSerie.forEach {
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = it.ejercicio.nombre)
                    Text(text = it.seriesRepeticiones)
                    Text(text = "Rir ${it.rir.toString()}")
                }
            }
        }
    }

}

@Composable
private fun PintaEnfoque(
   nombre:String
){

    Box(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth()
        .background(primaryLightColor)
    ) {
        Text(text = nombre, style = MaterialTheme.typography.h3)
    }


}