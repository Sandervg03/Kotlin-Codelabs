package com.example.lvl5task1

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lvl5task1.ui.screens.Screens
import com.example.lvl5task1.ui.screens.addscreen.AddScreen
import com.example.lvl5task1.ui.screens.homescreen.HomeScreen
import com.example.lvl5task1.ui.theme.Lvl5Task1Theme
import com.example.lvl5task1.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lvl5Task1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    
    @Composable
    private fun AppNavHost(
        navController: NavHostController,
        modifier: Modifier
        ) {
        val application = application
        val viewModel: GameViewModel = GameViewModel(application)

        NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

            composable(route = Screens.HomeScreen.route) {
                HomeScreen().Create(navController = navController, viewModel = viewModel)
            }

            composable(route = Screens.AddScreen.route) {
                AddScreen().Create(navController = navController, viewModel = viewModel)
            }
        }
    }
}