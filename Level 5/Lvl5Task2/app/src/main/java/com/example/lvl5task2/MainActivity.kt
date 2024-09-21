package com.example.lvl5task2

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lvl5task2.ui.composable.AppNavHost
import com.example.lvl5task2.ui.composable.BottomNavBar
import com.example.lvl5task2.ui.theme.Lvl5Task2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lvl5Task2Theme {
                val navController: NavHostController = rememberNavController()
                val application: Application = application
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { 
                                Text(text = stringResource(id = R.string.app_name)) 
                                    }, 
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Blue,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    bottomBar = {
                        BottomNavBar().Create(navController = navController)
                    },
                    modifier = Modifier
                        .fillMaxSize()) { innerPadding ->
                    AppNavHost().Create(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        application = application
                    )
                }
            }
        }
    }
}
