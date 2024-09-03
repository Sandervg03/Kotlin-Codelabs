package com.example.lvl4example.data.api

import com.example.lvl4example.data.model.RandomNumber
import retrofit2.http.GET

interface NumbersApiService {

    @GET("/random/${NumbersApi.numberType}?json")
    suspend fun getRandomNumber(): RandomNumber
}