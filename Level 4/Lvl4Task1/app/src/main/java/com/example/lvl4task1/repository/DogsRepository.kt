package com.example.lvl4task1.repository

import android.util.Log
import com.example.lvl4task1.data.api.Api
import com.example.lvl4task1.data.api.ApiService
import com.example.lvl4task1.data.api.util.Resource
import com.example.lvl4task1.data.model.Dog
import kotlinx.coroutines.withTimeout

class DogsRepository {

    private val _dogsApiService: ApiService = Api.dogsClient

    suspend fun getDog(): Resource<Dog> {
        val response = try {
            withTimeout(5_000) {
                _dogsApiService.getRandomDog()
            }
        } catch (e: Exception) {
            Log.e("DogsRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured while fetching data from the dogsapi.")
        }
        return Resource.Success(response)
    }

}