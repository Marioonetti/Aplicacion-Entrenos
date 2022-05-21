package com.example.aplicacionentrenos.ui.screens.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.aplicacionentrenos.domain.model.bo.BottomBarItem
import com.example.aplicacionentrenos.ui.theme.GrisFondo
import com.example.aplicacionentrenos.utils.UserCache

@Composable
fun BottomBarNavigation(
    items: List<BottomBarItem>,
    navController: NavController,
    onItemClick: (BottomBarItem) -> Unit,
    bottomBarState: MutableState<Boolean>
) {
    
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            val backStackEntry = navController.currentBackStackEntryAsState()
            BottomNavigation(
                elevation = 5.dp
            ) {
                items.forEach { item ->
                    val selected = item.ruta == backStackEntry.value?.destination?.route
                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (item.nombre == "Logout"){
                                UserCache.username =""
                                UserCache.token = ""
                                UserCache.password = ""
                                UserCache.id = null
                            }
                            onItemClick(item) },
                        selectedContentColor = Color.Blue,
                        unselectedContentColor = Color.White,
                        icon = {
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally)
                            {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.nombre,
                                )
                                Text(
                                    text = item.nombre,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp
                                )

                            }

                        }
                    )
                }
            }
        })



}