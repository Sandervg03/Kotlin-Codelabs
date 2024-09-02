package com.example.lvl3task1.viewModel

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private val _randomlyChosenGame = mutableStateOf(String())

    private var _gameRatingAccordingToUser = mutableFloatStateOf(5f)

    fun randomlyAssessableGame() {
        saveRandomlyChosenGame(
            listOf(
                "Red Dead Redemption 2",
                "Rocket League",
                "Shadow of the Tombraider"
            ).random()
        )
    }

    private fun saveRandomlyChosenGame(game: String) {
        _randomlyChosenGame.value = game
    }

    fun readRandomlyChosenGame(): String {
        return _randomlyChosenGame.value
    }

    fun readGameRatingAccordingToUser(): Float {
        return _gameRatingAccordingToUser.value
    }

    fun saveGameRatingAccordingToUser(rating: Float) {
        _gameRatingAccordingToUser.value = rating
    }

    fun reset() {
        _randomlyChosenGame.value = String()
        saveGameRatingAccordingToUser(5f)
    }
}