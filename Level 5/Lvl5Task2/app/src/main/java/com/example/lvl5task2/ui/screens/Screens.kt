package com.example.lvl5task2.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val description: String,
    val route: String,
    val icon: ImageVector
) {
    object gameScreen:
        Screens("Play", "GameScreen", Icons.Rounded.PlayArrow)

    object historyScreen:
            Screens("History", "HistoryScreen", Icons.Rounded.Refresh)
}