package com.example.lvl3task1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lvl3task1.ui.screens.RatingScreen
import com.example.lvl3task1.ui.screens.StartScreen
import com.example.lvl3task1.ui.screens.SummaryScreen
import com.example.lvl3task1.ui.theme.Lvl3Task1Theme
import com.example.lvl3task1.viewModel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lvl3Task1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    val viewModel: GameViewModel = viewModel()
    Column(modifier = modifier) {
        NavHost(navController = navController, startDestination = GameRatingScreensEnum.StartScreen.name) {

            composable(route = GameRatingScreensEnum.StartScreen.name) {
                StartScreen(navController = navController, viewModel = viewModel)
            }

            composable(route = GameRatingScreensEnum.RatingScreen.name) {
                RatingScreen(navController = navController, viewModel = viewModel)
            }

            composable(route = GameRatingScreensEnum.SummaryScreen.name) {
                SummaryScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}
