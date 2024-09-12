package com.example.lvl4task2.data.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")
    val name: String,

    @SerializedName("poster_path")
    val image: String,

    @SerializedName("vote_average")
    val rating: String,

    @SerializedName("overview")
    val description: String
)
