package com.example.lvl5task1.ui.screens.addscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lvl5task1.viewmodel.GameViewModel

class AddScreen {
    
    @Composable
    fun Create(
        navController: NavHostController,
        viewModel: GameViewModel
    ) {
        Column {
            Text(text = "Test")
        }
    }
}