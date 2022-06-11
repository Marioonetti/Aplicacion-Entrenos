package com.example.aplicacionentrenos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplicacionentrenos.domain.model.bo.BottomBarItem
import com.example.aplicacionentrenos.ui.MyEntrenosApp
import com.example.aplicacionentrenos.ui.screens.ejercicios.detalles.EjercicioDetalleScreen
import com.example.aplicacionentrenos.ui.screens.ejercicios.general.EjerciciosScreen
import com.example.aplicacionentrenos.ui.screens.entrenador.detalles.EntrenadorDetallesScreen
import com.example.aplicacionentrenos.ui.screens.entrenador.general.EntrenadoresScreen
import com.example.aplicacionentrenos.ui.screens.entrenamientos.detalles.EntrenoDetallesScreen
import com.example.aplicacionentrenos.ui.screens.entrenamientos.general.EntrenosScreen
import com.example.aplicacionentrenos.ui.screens.main.MainScreen
import com.example.aplicacionentrenos.ui.screens.perfil.PerfilScreen
import com.example.aplicacionentrenos.ui.screens.registro.RegistroScreen
import com.example.aplicacionentrenos.ui.screens.shared.BottomBarNavigation
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.NavigationConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyEntrenosApp {
                MyApp()
            }
        }
    }


    @Composable
    fun MyApp() {
        val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            NavigationConstants.MAIN_ROUTE -> {
                bottomBarState.value = false
            }
            NavigationConstants.REGISTRO_ROUTE -> {
                bottomBarState.value = false
            }
            NavigationConstants.ENTRENAMIENTOS_SCREEN_ROUTE -> {
                bottomBarState.value = true
            }
        }

        Scaffold(bottomBar = {
            BottomBarNavigation(
                items = listOf(
                    BottomBarItem(
                        Constantes.EJERCICIOS_NAME,
                        NavigationConstants.EJERCICIOS_ROUTE,
                        Icons.Default.FitnessCenter
                    ),

                    BottomBarItem(
                        Constantes.EENTRENADORES_NAME,
                        NavigationConstants.ENTRENADORES_ROUTE,
                        Icons.Default.DirectionsRun
                    ),
                    BottomBarItem(
                        Constantes.ENTRENAMIENTO_NAME,
                        NavigationConstants.ENTRENAMIENTOS_SCREEN_ROUTE,
                        Icons.Default.Description
                    ),
                    BottomBarItem(
                        Constantes.PERFIL_NAME,
                        NavigationConstants.PERFIL_ROUTE,
                        Icons.Default.AccountCircle
                    ),
                    BottomBarItem(
                        Constantes.LOGOUT,
                        NavigationConstants.MAIN_ROUTE,
                        Icons.Default.Logout
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.ruta)
                },
                bottomBarState = bottomBarState
            )
        }, content = {
            Box(modifier = Modifier.padding(it)) {
                Navigation(navController)
            }
        }
        )

    }

}


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationConstants.MAIN_ROUTE
    )
    {
        composable(NavigationConstants.MAIN_ROUTE) {
            MainScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(NavigationConstants.ENTRENAMIENTOS_SCREEN_ROUTE) {
            EntrenosScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(NavigationConstants.REGISTRO_ROUTE) {
            RegistroScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(NavigationConstants.ENTRENADORES_ROUTE) {
            EntrenadoresScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(NavigationConstants.PERFIL_ROUTE) {
            PerfilScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(NavigationConstants.EJERCICIOS_ROUTE) {
            EjerciciosScreen(
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(
            route = NavigationConstants.DETALLES_ENTRENADOR,
            arguments = listOf(
                navArgument(
                    NavigationConstants.DETALLES_ENTRENADOR_ID_PARAM
                )
                {
                    type = NavType.IntType
                })
        ) {
            val id =
                it.arguments?.getInt(NavigationConstants.DETALLES_ENTRENADOR_ID_PARAM)

            EntrenadorDetallesScreen(
                onPopBackStack = { navController.popBackStack() },
                id
            )
        }
        composable(
            route = NavigationConstants.DETALLE_ENTRENOS_ROUTE,
            arguments = listOf(
                navArgument(
                    NavigationConstants.DETALLE_ENTRENOS_PARAM
                )
                {
                    type = NavType.IntType
                })
        ) { navStack ->
            val id =
                navStack.arguments?.getInt(NavigationConstants.DETALLE_ENTRENOS_PARAM)

            EntrenoDetallesScreen(
                onNavigate = { navController.navigate(it.route) },
                id
            )
        }
        composable(
            route = NavigationConstants.DETALLE_EJERCICIO_ROUTE,
            arguments = listOf(
                navArgument(
                    NavigationConstants.DETALLE_EJERCICIO_PARAM
                )
                {
                    type = NavType.IntType
                })
        ) {
            val id =
                it.arguments?.getInt(NavigationConstants.DETALLE_EJERCICIO_PARAM)

            EjercicioDetalleScreen(
                onPopBackStack = { navController.popBackStack() },
                id
            )
        }
    }
}