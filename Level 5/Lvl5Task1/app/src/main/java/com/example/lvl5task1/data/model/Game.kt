package com.example.lvl5task1.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("game")
data class Game(

    @ColumnInfo("title")
    var title: String,

    @ColumnInfo("platform")
    var platform: String,

    @ColumnInfo("release")
    var release: Date,

    @PrimaryKey(true)
    @ColumnInfo("id")
    var id: Int = 0
)
