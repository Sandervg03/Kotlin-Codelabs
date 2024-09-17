package com.example.lvl5example.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lvl5example.data.ShoppingListItem

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM ShoppingListTable")
    fun getAllShoppingListItems(): LiveData<List<ShoppingListItem>>

    @Insert
    suspend fun addShoppingListItem(shoppingListItem: ShoppingListItem)

    @Delete
    suspend fun removeShoppingListItem(shoppingListItem: ShoppingListItem)

    @Query("DELETE FROM ShoppingListTable")
    suspend fun removeAllShoppingListItems()

    @Update
    suspend fun updateShoppingListItem(shoppingListItem: ShoppingListItem)

}