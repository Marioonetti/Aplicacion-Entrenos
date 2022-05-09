package com.example.aplicacionentrenos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionentrenos.ui.screens.ejercicios.EjerciciosScreen
import com.example.aplicacionentrenos.ui.screens.entrenadores.EntrenadoresScreen
import com.example.aplicacionentrenos.ui.screens.main.MainScreen
import com.example.aplicacionentrenos.ui.screens.perfil.PerfilScreen
import com.example.aplicacionentrenos.ui.screens.principal.PrincipalScreen
import com.example.aplicacionentrenos.ui.screens.registro.RegistroScreen
import com.example.aplicacionentrenos.utils.NavigationConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,
                startDestination = NavigationConstants.MAIN_ROUTE)
            {
                composable(NavigationConstants.MAIN_ROUTE){
                    MainScreen(
                        onNavigate = { navController.navigate(it.route)}
                    )
                }
                composable(NavigationConstants.PRINCIPAL_SCREEN_ROUTE){
                    PrincipalScreen(
                        onNavigate = { navController.navigate(it.route)},
                        navController
                    )
                }
                composable(NavigationConstants.REGISTRO_ROUTE){
                    RegistroScreen(
                        onNavigate = { navController.navigate(it.route)}
                    )
                }
                composable(NavigationConstants.ENTRENADORES_ROUTE){
                    EntrenadoresScreen(
                        onNavigate = { navController.navigate(it.route)}
                    )
                }
                composable(NavigationConstants.PERFIL_ROUTE){
                    PerfilScreen(
                        onNavigate = { navController.navigate(it.route)},
                        navController
                    )
                }
                composable(NavigationConstants.EJERCICIOS_ROUTE){
                    EjerciciosScreen(
                        onNavigate = { navController.navigate(it.route)}
                    )
                }
            }

        }
    }
}
