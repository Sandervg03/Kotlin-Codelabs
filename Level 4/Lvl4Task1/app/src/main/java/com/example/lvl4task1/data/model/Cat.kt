package com.example.lvl4task1.data.model

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("createdAt") val creationDate: String,
    @SerializedName("_id") val urlExtension: String
)
