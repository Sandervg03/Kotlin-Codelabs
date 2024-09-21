package com.example.lvl5task2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lvl5task2.data.Game

@Dao
interface GameDao {

    @Query("SELECT * from GameDatabase")
    fun getGames(): LiveData<List<Game>>

    @Insert
    fun addGame(game: Game)

    @Insert
    fun addGames(games: List<Game>)

    @Delete
    fun deleteGame(game: Game)

    @Query("DELETE from GameDatabase")
    fun deleteGames()

    @Query("SELECT COUNT(*) FROM GameDatabase WHERE result = 'Win'")
    fun getGamesWonCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM GameDatabase WHERE result = 'Lose'")
    fun getGamesLostCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM GameDatabase WHERE result = 'Draw'")
    fun getGamesDrawCount(): LiveData<Int>
}