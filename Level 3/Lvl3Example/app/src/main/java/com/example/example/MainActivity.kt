package com.example.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.example.ui.screens.AddReminderScreen
import com.example.example.ui.screens.RemindersListScreen
import com.example.example.ui.screens.RemindersScreens
import com.example.example.ui.theme.ExampleTheme
import com.example.example.viewmodel.RemindersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExampleTheme {
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
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    val viewModel: RemindersViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RemindersScreens.RemindersList.name
    ){
        composable(route = RemindersScreens.RemindersList.name) {
            RemindersListScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = RemindersScreens.NewReminder.name) {
            AddReminderScreen(navController = navController, viewModel = viewModel)
        }
    }
}
