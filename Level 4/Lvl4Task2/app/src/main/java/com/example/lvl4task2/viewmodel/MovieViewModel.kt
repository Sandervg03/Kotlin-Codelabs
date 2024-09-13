package com.example.lvl4task2.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lvl4task2.data.api.util.Resource
import com.example.lvl4task2.data.models.Movie
import com.example.lvl4task2.data.models.ResponseResult
import com.example.lvl4task2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    private val _selectedMovie: MutableState<Movie?> =
        mutableStateOf<Movie?>(null)

    var selectedMovie: Movie?
        get() {
            return if (_selectedMovie.value != null) {
                _selectedMovie.value!!
            } else {
                null
            }
        }
        set(movie: Movie?) {
            _selectedMovie.value = movie
        }

    private val _movieRepository: MovieRepository = MovieRepository()

    val movieResource: LiveData<Resource<ResponseResult>>
        get() = _movieResource

    private val _movieResource: MutableLiveData<Resource<ResponseResult>> =
        MutableLiveData(Resource.Empty())

    fun updateMovieResource(movie: String) {
        _movieResource.value = Resource.Loading()

        viewModelScope.launch {
            _movieResource.value = _movieRepository.getMovie(movie)
        }
    }
}