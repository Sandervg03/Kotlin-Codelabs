package com.example.lvl5example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingListTable")
data class ShoppingListItem(

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "product")
    val product: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0

)
