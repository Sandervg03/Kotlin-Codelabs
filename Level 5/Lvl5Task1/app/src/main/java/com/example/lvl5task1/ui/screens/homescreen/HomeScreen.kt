package com.example.lvl5task1.ui.screens.homescreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lvl5task1.R
import com.example.lvl5task1.data.model.Game
import com.example.lvl5task1.viewmodel.GameViewModel

class HomeScreen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Create(
        navController: NavHostController,
        viewModel: GameViewModel
    ) {
        val context: Context = LocalContext.current
        val gamesBackLog: List<Game> = viewModel.gameBackLog
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val deletedBackLog: Boolean by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = stringResource(id = R.string.app_name))
                            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete")
                        }

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White
                    )
                )
            },
            content = { paddingValues ->
                ScreenContent(Modifier.padding(paddingValues))
            }
        )
    }

    @Composable
    private fun ScreenContent(modifier: Modifier){
        Column {
            Text(text = "Test")
        }
    }
}