package com.example.lvl4task2.repository

import android.util.Log
import com.example.lvl4task2.data.api.API_KEY
import com.example.lvl4task2.data.api.Api
import com.example.lvl4task2.data.api.ApiService
import com.example.lvl4task2.data.api.util.Response
import com.example.lvl4task2.data.models.ResponseResult
import kotlinx.coroutines.withTimeout

class MovieRepository {

    private val _api: ApiService = Api().createApi()

    private val _apiKey: String = API_KEY

    suspend fun getMovie(movie: String): Response<ResponseResult> {
        val response = try {
            withTimeout(5_000) {
                _api.getMovie(movie, _apiKey)
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", e.message ?: "No exception message available")
            return Response.Error("An unknown error occurred while fetching data for the movie: $movie")
        }
        Log.d("Result", response.results.toString())
        return Response.Success(response)
    }
}