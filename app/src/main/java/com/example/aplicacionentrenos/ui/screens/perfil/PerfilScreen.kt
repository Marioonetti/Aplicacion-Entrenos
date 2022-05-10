package com.example.aplicacionentrenos.ui.screens.perfil

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.utils.UiEvents


@Composable
fun PerfilScreen(onNavigate: (UiEvents.Navigate) -> Unit, navController: NavHostController,) {


    Text(text = "Perfil Screen")
}