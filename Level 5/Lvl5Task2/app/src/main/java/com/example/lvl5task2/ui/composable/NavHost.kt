package com.example.lvl5task2.ui.composable

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lvl5task2.ui.screens.GameScreen.GameScreen
import com.example.lvl5task2.ui.screens.HistoryScreen.HistoryScreen
import com.example.lvl5task2.ui.screens.Screens
import com.example.lvl5task2.viewmodel.GameViewModel

class AppNavHost {

    @Composable
    fun Create(navController: NavHostController, modifier: Modifier, application: Application) {
        Column(
            modifier = modifier
        ) {
            val viewModel: GameViewModel = GameViewModel(application)
            NavHost(navController = navController, startDestination = "Game") {
                navigation(route = "Game", startDestination = Screens.gameScreen.route) {
                    composable(route = Screens.gameScreen.route) {
                        GameScreen().Create(viewModel = viewModel)
                    }

                    composable(route = Screens.historyScreen.route) {
                        HistoryScreen().Create(viewModel = viewModel)
                    }
                }
            }
        }
    }
}