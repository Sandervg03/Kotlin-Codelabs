package com.example.lvl4task2.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lvl4task2.data.api.util.Response
import com.example.lvl4task2.data.models.Movie
import com.example.lvl4task2.data.models.ResponseResult
import com.example.lvl4task2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    private lateinit var _selectedMovie: MutableState<Movie>

    var selectedMovie: Movie
        get() = _selectedMovie.value
        set(movie){
            _selectedMovie.value = movie
        }

    private val _movieRepository: MovieRepository = MovieRepository()

    val movieResource: LiveData<Response<ResponseResult>>
        get() = _movieResource

    private val _movieResource: MutableLiveData<Response<ResponseResult>> =
        MutableLiveData(Response.Empty())

    fun getMovie(movie: String) {
        _movieResource.value = Response.Loading()

        viewModelScope.launch {
            _movieResource.value = _movieRepository.getMovie(movie)
        }
    }
}