package com.example.lvl4task2.ui.screens.MovieSearchScreen

import androidx.compose.material.icons.Icons
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.lvl4task2.R
import com.example.lvl4task2.data.api.util.Response
import com.example.lvl4task2.data.models.Movie
import com.example.lvl4task2.repository.MovieRepository
import com.example.lvl4task2.viewmodel.MovieViewModel

class MovieSearchScreen {

    @Composable
    fun Create(navController: NavHostController, viewModel: MovieViewModel) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.DarkGray)
        ) {
            val movieRepository: MovieRepository = MovieRepository()
            SearchBar(viewModel = viewModel, movieRepository = movieRepository )
            MovieSearchResults(navController = navController)
        }
    }

    @Composable
    private fun SearchBar(viewModel: MovieViewModel, movieRepository: MovieRepository) {
        var userInput: String by remember {
            mutableStateOf("")
        }
        TextField(
            value = userInput,
            onValueChange = {
                userInput = it
                            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_bar),
                    color = Color.White
                )
                          },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = "Clear",
                    modifier = Modifier
                        .clickable {
                            userInput = ""
                                   },
                    tint = Color.White
                )
                          },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        if (userInput.isNotBlank()) {
                            viewModel.getMovie(userInput)
                        }
                    }
                )
            }
        )
    }

    @Composable
    private fun MovieSearchResults(
        navController: NavHostController,
        viewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    ){
        val movieResource: Response<List<Movie>>? by viewModel.movieResource.observeAsState()

    }
}