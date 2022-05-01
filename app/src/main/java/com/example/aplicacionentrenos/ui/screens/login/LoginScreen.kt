package com.example.aplicacionentrenos.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.ui.theme.AzulLogin
import com.example.aplicacionentrenos.utils.NavigationConstants

@Composable
fun LoginBox(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Nombre de Usuario: ",
                modifier = Modifier.align(Alignment.CenterHorizontally))
            OutlinedTextField(
                value = viewModel.username,
                onValueChange = {
                                viewModel.handleEvent(LoginContract.Eventos.onUsernameChange(it))
                },
                label = { Text(text = "Nombre de Usuario")},
                singleLine = true
            )
        }

        Column() {
            Text(text = "Contraseña: ",
                modifier = Modifier.align(Alignment.CenterHorizontally))
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = {
                    viewModel.handleEvent(LoginContract.Eventos.onPasswordChange(it))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                label = { Text(text = "Contraseña")},
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {navController.navigate(NavigationConstants.PRINCIPAL_SCREEN_ROUTE) }) {
                Text(text = "Registrarse")
            }
            Button(onClick = {navController.navigate(NavigationConstants.PRINCIPAL_SCREEN_ROUTE) }) {
                Text(text = "Login")
            }
        }

    }




}