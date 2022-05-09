package com.example.aplicacionentrenos.ui.screens.perfil

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Train
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.domain.model.BottomBarItem
import com.example.aplicacionentrenos.ui.screens.shared.BottomBarNavigation
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents


@Composable
fun PerfilScreen(onNavigate: (UiEvents.Navigate) -> Unit, navController: NavHostController,) {

    Scaffold(
        bottomBar = {
            BottomBarNavigation(
                items = listOf(
                    BottomBarItem(
                        "Home",
                        NavigationConstants.PRINCIPAL_SCREEN_ROUTE,
                        Icons.Default.Person
                    ),
                    BottomBarItem(
                        "Entrenadores",
                        NavigationConstants.ENTRENADORES_ROUTE,
                        Icons.Default.Person
                    ),
                    BottomBarItem(
                        "Ejercicios",
                        NavigationConstants.EJERCICIOS_ROUTE,
                        Icons.Default.Train
                    ),
                    BottomBarItem(
                        "Perfil",
                        NavigationConstants.PERFIL_ROUTE,
                        Icons.Default.PersonAdd
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.ruta)
                }
            )
        }
    ) {

    }
    Text(text = "Perfil Screen")
}