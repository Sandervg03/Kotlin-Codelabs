package com.example.lvl4task2.data.api

import com.example.lvl4task2.data.api.util.Response
import com.example.lvl4task2.data.models.Movie
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("/search/movie?")
    fun getMovie(
        @Query("query") movie: String,
        @Header("Authorization")apiKey: String
    ): Response<List<Movie>>

}