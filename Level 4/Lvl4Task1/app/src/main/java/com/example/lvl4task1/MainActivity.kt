package com.example.lvl4task1

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lvl4task1.ui.composables.BottomNavBar
import com.example.lvl4task1.ui.screens.PetsScreens
import com.example.lvl4task1.ui.screens.catsScreen.CatsScreen
import com.example.lvl4task1.ui.screens.dogsScreen.DogsScreen
import com.example.lvl4task1.ui.theme.Lvl4Task1Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lvl4Task1Theme {
                val navController: NavHostController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(
                                text = stringResource(id = R.string.app_name)
                            )
                        },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Blue,
                                titleContentColor = Color.White
                            ))
                    },
                    bottomBar = {
                        BottomNavBar(navController = navController)
                                },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = navController, modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        NavHost(navController = navController, startDestination = PetsScreens.CatsScreen.route) {

            composable(route = PetsScreens.CatsScreen.route) {
                CatsScreen()
            }

            composable(route = PetsScreens.DogsScreen.route) {
                DogsScreen()
            }

        }
    }
}