package com.example.lvl4task2

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lvl4task2.ui.screens.MovieDetailsScreen.MovieDetailsScreen
import com.example.lvl4task2.ui.screens.MovieLibraryScreens
import com.example.lvl4task2.ui.screens.MovieSearchScreen.MovieSearchScreen
import com.example.lvl4task2.ui.theme.Lvl4Task2Theme
import com.example.lvl4task2.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lvl4Task2Theme {
                val navController: NavHostController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = MovieLibraryScreens.MovieSearchScreen.name
        ) {
            val movieViewModel: MovieViewModel = MovieViewModel()

            composable(route = MovieLibraryScreens.MovieSearchScreen.name) {
                val movieSearchScreen: MovieSearchScreen = MovieSearchScreen()
                movieSearchScreen.Create(navController = navController, viewModel = movieViewModel)
            }
            
            composable(route = MovieLibraryScreens.MovieDetailsScreen.name) {
                val movieDetailsScreen: MovieDetailsScreen = MovieDetailsScreen()
                movieDetailsScreen.Create(navController = navController, viewModel = movieViewModel)
            }
        }
    }
}