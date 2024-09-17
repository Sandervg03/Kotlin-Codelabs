package com.example.lvl5example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lvl5example.data.ShoppingListItem

@Database(entities = [ShoppingListItem::class], version = 1, exportSchema = false)
abstract class ShoppingListDatabase: RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao

    companion object {

        private val DATABASE_NAME = "SHOPPINGLIST_DATABASE"

        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        fun getDatabase(context: Context): ShoppingListDatabase {
            return INSTANCE ?: synchronized(this) {
                    val instance: ShoppingListDatabase = Room.databaseBuilder(
                        context = context.applicationContext,
                        klass = ShoppingListDatabase::class.java,
                        name = DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                    instance
            }
        }
    }
}