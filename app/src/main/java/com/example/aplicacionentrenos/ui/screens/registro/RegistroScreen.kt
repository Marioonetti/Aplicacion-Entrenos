package com.example.aplicacionentrenos.ui.screens.registro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect

@Composable
fun RegistroScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: RegistroViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
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

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(320.dp)
                .height(600.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                OutlinedTextField(
                    value = viewModel.taNombre,
                    onValueChange = {
                        viewModel.handleEvent(RegistroContract.Eventos.OnNombreChange(it))
                    },
                    label = { Text(text = Constantes.NOMBRE) },
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.taApellidos,
                    onValueChange = {
                        viewModel.handleEvent(RegistroContract.Eventos.OnApellidosChange(it))
                    },
                    label = { Text(text = Constantes.APELLIDOS) },
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.taUsername,
                    onValueChange = {
                        viewModel.handleEvent(RegistroContract.Eventos.OnUsernameChange(it))
                    },
                    label = { Text(text = Constantes.USUARIO) },
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.taPassw,
                    onValueChange = {
                        viewModel.handleEvent(RegistroContract.Eventos.OnPasswordChange(it))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    label = { Text(text = Constantes.PASSW) },
                    visualTransformation = if (viewModel.hidden)
                        PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(onClick = { viewModel.hidden = !viewModel.hidden }) {
                            val vector: ImageVector =
                                if (viewModel.hidden) Icons.Default.VisibilityOff
                                else Icons.Default.Visibility
                            Icon(
                                imageVector = vector,
                                contentDescription = Constantes.CONTENT_DESCRIPTION
                            )
                        }
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        viewModel.handleEvent(RegistroContract.Eventos.NavigateToLogin)
                    }) {
                        Text(text = Constantes.LOGINN)
                    }

                    Button(onClick = {
                        viewModel.handleEvent(
                            RegistroContract.Eventos.Registrarse(
                                ClienteDTO(
                                    viewModel.taUsername,
                                    viewModel.taPassw,
                                    viewModel.taNombre,
                                    viewModel.taApellidos
                                )
                            )
                        )
                    }) {
                        Text(text = Constantes.REGISTRARSE)
                    }

                }
                LoadProgressBar(viewModel.loading)

            }
        }
    }


}