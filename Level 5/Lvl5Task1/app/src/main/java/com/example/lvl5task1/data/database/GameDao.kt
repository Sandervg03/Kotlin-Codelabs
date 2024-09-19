package com.example.lvl5task1.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lvl5task1.data.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game ORDER BY `release` ASC")
    fun getGames(): LiveData<List<Game>>

    @Insert
    suspend fun addGame(game: Game)

    @Insert
    suspend fun addGames(games: List<Game>)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM game")
    suspend fun deleteAllGames()
}