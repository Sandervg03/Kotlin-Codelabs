package com.example.lvl4task2.data.models

import com.google.gson.annotations.SerializedName

data class ResponseResult(
    @SerializedName("results")
    val results: List<Movie>,

    @SerializedName("pages")
    val page: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)
