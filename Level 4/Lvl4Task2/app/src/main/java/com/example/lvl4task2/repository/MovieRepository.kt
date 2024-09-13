package com.example.lvl4task2.repository

import android.util.Log
import com.example.lvl4task2.data.api.API_KEY
import com.example.lvl4task2.data.api.Api
import com.example.lvl4task2.data.api.ApiService
import com.example.lvl4task2.data.api.util.Resource
import com.example.lvl4task2.data.models.Movie
import com.example.lvl4task2.data.models.ResponseResult
import kotlinx.coroutines.withTimeout

class MovieRepository {

    private val _apiService: ApiService = Api().createApi()

    private val _apiKey: String = API_KEY

    suspend fun getMovie(movie: String): Resource<ResponseResult> {
        val response = try {
            withTimeout(5_000) {
                _apiService.getMovie(movie, _apiKey)
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occurred while fetching data for the movie: $movie")
        }
        Log.d("Result", response.results.toString())
        return Resource.Success(response)
    }
}