package com.example.lvl4example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lvl4example.ui.screens.NumbersHistoryScreen
import com.example.lvl4example.ui.screens.NumbersScreen
import com.example.lvl4example.ui.theme.Lvl4ExampleTheme
import com.example.lvl4example.viewmodel.NumbersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lvl4ExampleTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }

    @Composable
    fun AppNavHost(navController: NavHostController) {

        val viewModel: NumbersViewModel = viewModel()

        NavHost(
            navController = navController,
            startDestination = NumbersScreens.NumbersScreen.name
        ) {

            composable(NumbersScreens.NumbersScreen.name) {
                NumbersScreen(navController = navController, viewModel = viewModel)
            }

            composable(NumbersScreens.NumbersHistoryScreen.name) {
                NumbersHistoryScreen(navController = navController)
            }
        }
    }
}