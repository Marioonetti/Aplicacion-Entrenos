package com.example.aplicacionentrenos.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.ui.MyEntrenosApp
import com.example.aplicacionentrenos.ui.screens.login.LoginBox
import com.example.aplicacionentrenos.ui.theme.AzulLogin
import com.example.aplicacionentrenos.ui.theme.GrisFondo
import com.example.aplicacionentrenos.ui.theme.secondaryLightColor

@Composable
fun MainScreen(navController: NavHostController) {
    MyEntrenosApp {
        Scaffold { padding ->
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {

                Box(modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(320.dp)
                    .height(550.dp)
                    .background(GrisFondo)
                    .shadow(2.dp, shape = RoundedCornerShape(5.dp))
                    ) {
                    LoginBox(navController)
                }
            }


        }
    }

}