package com.example.lvl4example.repository

import android.util.Log
import com.example.lvl4example.data.api.NumbersApi
import com.example.lvl4example.data.api.NumbersApiService
import com.example.lvl4example.data.api.util.Resource
import com.example.lvl4example.data.model.RandomNumber
import kotlinx.coroutines.withTimeout

class NumbersRepository {

    private val _numbersApiService: NumbersApiService = NumbersApi.createApi()

    suspend fun getRandomNumber(): Resource<RandomNumber> {
        val response = try {
            withTimeout(5_000) {
                _numbersApiService.getRandomNumber()
            }
        } catch (e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured while fetching data from the numbersapi.")
        }
        return Resource.Success(response)
    }

}