package com.example.lvl5task2.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lvl5task2.data.Game
import com.example.lvl5task2.data.converter.Converters

@androidx.room.Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {

    abstract fun getDao(): GameDao

    companion object {

        private val DATABASE_NAME: String = "GAME_DATABASE"

        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database {
            return INSTANCE ?: synchronized(this) {
                val instance: Database = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = Database::class.java,
                    name = DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}