package com.example.lvl4task1.repository

import android.util.Log
import com.example.lvl4task1.data.api.Api
import com.example.lvl4task1.data.api.ApiService
import com.example.lvl4task1.data.api.util.Resource
import com.example.lvl4task1.data.model.Cat
import kotlinx.coroutines.withTimeout

class CatsRepository {

    private val _catsApiService: ApiService = Api.catsClient

    suspend fun getCat(): Resource<Cat> {
        val response = try {
            withTimeout(5_000) {
                _catsApiService.getRandomCat()
            }
        } catch (e: Exception) {
            Log.e("CatsRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured while fetching data from the catsapi.")
        }
        return Resource.Success(response)
    }

}