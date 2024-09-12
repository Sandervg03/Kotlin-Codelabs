package com.example.lvl4task2.data.api

import com.example.lvl4task2.data.models.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie?include_adult=false&language=en-US&page=1")
    suspend fun getMovie(
        @Query("query") movie: String,
        @Header("Authorization") apiKey: String
    ): ResponseResult

}