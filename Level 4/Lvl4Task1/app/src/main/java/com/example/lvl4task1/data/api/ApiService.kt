package com.example.lvl4task1.data.api

import com.example.lvl4task1.data.model.Cat
import com.example.lvl4task1.data.model.Dog
import retrofit2.http.GET

interface ApiService {
    @GET("/cat?json=true")
    suspend fun getRandomCat(): Cat

    @GET("/api/breeds/image/random")
    suspend fun getRandomDog(): Dog
}