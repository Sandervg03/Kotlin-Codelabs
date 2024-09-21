package com.example.lvl5task2.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lvl5task2.ui.screens.Screens
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class BottomNavBar {

    @Composable
    fun Create(navController: NavHostController) {

        val screens: List<Screens> = listOf(
            Screens.gameScreen,
            Screens.historyScreen
        )
        val navBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStack?.destination

        BottomAppBar {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                screens.forEach{ screen ->
                    NavigationBarItem(
                        selected = screen.route == currentDestination?.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }},
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.description
                            )
                        },
                        label = {
                            Text(text = screen.description)
                        }
                    )
                }
            }
        }
    }
}