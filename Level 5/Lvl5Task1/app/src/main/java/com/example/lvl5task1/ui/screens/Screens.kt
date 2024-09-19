package com.example.lvl5task1.ui.screens

sealed class Screens(
    val route: String
) {
    data object HomeScreen: Screens("homeScreen")
    data object AddScreen: Screens("addScreen")
}