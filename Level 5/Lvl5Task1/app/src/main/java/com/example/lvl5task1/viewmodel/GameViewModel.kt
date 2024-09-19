package com.example.lvl5task1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lvl5task1.data.model.Game
import com.example.lvl5task1.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application): AndroidViewModel(application) {

    private val repository: GameRepository = GameRepository(application.applicationContext)

    private val mainScope = CoroutineScope(Dispatchers.IO)

    val gameBackLog: List<Game> = repository.getGames()

    fun addGame(game: Game) {
        mainScope.launch {
            repository.addGame(game)
        }
    }

    fun addGames(games: List<Game>) {
        mainScope.launch {
            repository.addGames(games)
        }
    }

    fun deleteGame(game: Game) {
        mainScope.launch {
            repository.delete(game)
        }
    }

    fun deleteAllGames() {
        mainScope.launch {
            repository.deleteAllGames()
        }
    }

}