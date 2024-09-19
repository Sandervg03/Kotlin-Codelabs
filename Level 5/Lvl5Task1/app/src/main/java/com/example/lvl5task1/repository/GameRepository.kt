package com.example.lvl5task1.repository

import android.content.Context
import com.example.lvl5task1.data.database.GameDao
import com.example.lvl5task1.data.database.GameRoomDatabase
import com.example.lvl5task1.data.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database: GameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = database.gameDao()
    }

    suspend fun addGame(game: Game) {
        return gameDao.addGame(game)
    }

    suspend fun addGames(games: List<Game>) {
        return gameDao.addGames(games)
    }

    suspend fun delete(game: Game) {
        return gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        return gameDao.deleteAllGames()
    }

    fun getGames(): List<Game> {
        return gameDao.getGames()
    }

}