package com.example.lvl5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.lvl5task2.database.Database
import com.example.lvl5task2.data.Game
import com.example.lvl5task2.database.GameDao

class Repository(
    private val context: Context
) {

    private val database: Database = Database.getDatabase(context)

    private val dao: GameDao = database.getDao()

    fun getWinsCount(): LiveData<Int> {
        return dao.getGamesWonCount()
    }

    fun getLossCount(): LiveData<Int> {
        return dao.getGamesLostCount()
    }

    fun getDrawCount(): LiveData<Int> {
        return dao.getGamesDrawCount()
    }

    fun getGames(): LiveData<List<Game>> {
        return dao.getGames()
    }

    fun addGame(game: Game) {
        dao.addGame(game)
    }

    fun addGames(games: List<Game>) {
        dao.addGames(games)
    }

    fun deleteGame(game: Game) {
        dao.deleteGame(game)
    }

    fun deleteGames() {
        dao.deleteGames()
    }
}