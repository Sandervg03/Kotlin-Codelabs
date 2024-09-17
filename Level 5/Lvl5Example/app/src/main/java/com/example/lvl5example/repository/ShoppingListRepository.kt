package com.example.lvl5example.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.lvl5example.database.ShoppingListDao
import com.example.lvl5example.database.ShoppingListDatabase
import com.example.lvl5example.data.ShoppingListItem

class ShoppingListRepository(context: Context) {

    private var _shoppingListDao: ShoppingListDao

    init {
        val shoppingListDatabase: ShoppingListDatabase = ShoppingListDatabase.getDatabase(context)
        _shoppingListDao = shoppingListDatabase.shoppingListDao()
    }

    fun getAllShoppingListItems(): LiveData<List<ShoppingListItem>> {
        return _shoppingListDao.getAllShoppingListItems()
    }

    suspend fun addShoppingListItem(shoppingListItem: ShoppingListItem): Unit {
        _shoppingListDao.addShoppingListItem(shoppingListItem)
    }

    suspend fun removeShoppingListItem(shoppingListItem: ShoppingListItem): Unit {
        _shoppingListDao.removeShoppingListItem(shoppingListItem)
    }

    suspend fun removeAllShoppingListItems(): Unit {
        _shoppingListDao.removeAllShoppingListItems()
    }

    suspend fun updateShoppingListItem(shoppingListItem: ShoppingListItem): Unit {
        _shoppingListDao.updateShoppingListItem(shoppingListItem)
    }
}