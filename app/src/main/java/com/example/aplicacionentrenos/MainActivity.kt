package com.example.aplicacionentrenos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionentrenos.ui.screens.main.MainScreen
import com.example.aplicacionentrenos.ui.screens.principal.PrincipalScreen
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
                    MainScreen(navController)
                }
                composable(NavigationConstants.PRINCIPAL_SCREEN_ROUTE){
                    PrincipalScreen(navController)
                }
            }

        }
    }
}
