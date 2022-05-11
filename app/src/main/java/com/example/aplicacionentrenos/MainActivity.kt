package com.example.aplicacionentrenos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Train
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplicacionentrenos.domain.model.bo.BottomBarItem
import com.example.aplicacionentrenos.ui.screens.ejercicios.EjerciciosScreen
import com.example.aplicacionentrenos.ui.screens.entrenador.detalles.EntrenadorDetallesScreen
import com.example.aplicacionentrenos.ui.screens.entrenador.general.EntrenadoresScreen
import com.example.aplicacionentrenos.ui.screens.main.MainScreen
import com.example.aplicacionentrenos.ui.screens.perfil.PerfilScreen
import com.example.aplicacionentrenos.ui.screens.principal.PrincipalScreen
import com.example.aplicacionentrenos.ui.screens.registro.RegistroScreen
import com.example.aplicacionentrenos.ui.screens.shared.BottomBarNavigation
import com.example.aplicacionentrenos.utils.NavigationConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }


    @Composable
    fun MyApp(){
        val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            NavigationConstants.MAIN_ROUTE ->{
                bottomBarState.value = false
            }
            NavigationConstants.REGISTRO_ROUTE ->{
                bottomBarState.value = false
            }
            NavigationConstants.PRINCIPAL_SCREEN_ROUTE ->{
                bottomBarState.value = true
            }
        }


        Scaffold (bottomBar = {
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
                    },
                    bottomBarState = bottomBarState
                )
            }
        , content = {
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
                    composable(
                        route = NavigationConstants.DETALLES_ENTRENADOR,
                        arguments = listOf(
                            navArgument(
                                NavigationConstants.DETALLES_ENTRENADOR_ID_PARAM)
                            {
                                type = NavType.IntType
                            })
                    ){
                        val id =
                            it.arguments?.getInt(NavigationConstants.DETALLES_ENTRENADOR_ID_PARAM)

                        EntrenadorDetallesScreen(
                            id
                        )
                    }
                }

            }

        )

    }

}
