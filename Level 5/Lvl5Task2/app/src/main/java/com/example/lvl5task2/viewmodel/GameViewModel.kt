package com.example.lvl5task2.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.lvl5task2.data.Game
import com.example.lvl5task2.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application): AndroidViewModel(application) {

    private val _IOSCOPE = CoroutineScope(Dispatchers.IO)
    private val repository: Repository = Repository(application.applicationContext)

    val games: LiveData<List<Game>> = repository.getGames()

    val winsCount: LiveData<Int> = repository.getWinsCount()
    val lossCount: LiveData<Int> = repository.getLossCount()
    val drawCount: LiveData<Int> = repository.getDrawCount()

    fun addGame(game: Game) {
        _IOSCOPE.launch {
            repository.addGame(game)
        }
    }

    fun addGames(games: List<Game>) {
        _IOSCOPE.launch {
            repository.addGames(games)
        }
    }

    fun deleteGame(game: Game) {
        _IOSCOPE.launch {
            repository.deleteGame(game)
        }
    }

    fun deleteGames() {
        _IOSCOPE.launch {
            repository.deleteGames()
        }
    }
}