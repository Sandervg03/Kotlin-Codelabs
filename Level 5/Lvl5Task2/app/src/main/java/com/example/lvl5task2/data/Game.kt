package com.example.lvl5task2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("GameDatabase")
data class Game(

    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("date")
    val date: Date,

    @ColumnInfo("computerMove")
    val computer: String,

    @ColumnInfo("playerMove")
    val player: String,

    @ColumnInfo("result")
    val result: String
)
