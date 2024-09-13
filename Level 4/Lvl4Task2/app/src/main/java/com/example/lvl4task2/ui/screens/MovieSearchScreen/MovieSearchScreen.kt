package com.example.lvl4task2.ui.screens.MovieSearchScreen

import androidx.compose.material.icons.Icons
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lvl4task2.R
import com.example.lvl4task2.data.api.util.Resource
import com.example.lvl4task2.data.models.ResponseResult
import com.example.lvl4task2.ui.screens.MovieLibraryScreens
import com.example.lvl4task2.viewmodel.MovieViewModel

class MovieSearchScreen {

    @Composable
    fun Create(navController: NavHostController, viewModel: MovieViewModel) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.DarkGray)
        ) {
            val movieResource: Resource<ResponseResult>? by viewModel.movieResource.observeAsState()
            SearchBar(viewModel = viewModel)
            MovieSearchResults(
                navController = navController,
                movieResource = movieResource,
                viewModel = viewModel
            )
        }
    }

    @Composable
    private fun SearchBar(viewModel: MovieViewModel) {
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
                            viewModel.updateMovieResource(userInput)
                        }
                    }
                )
            }
        )
    }

    @Composable
    private fun MovieSearchResults(
        navController: NavHostController,
        movieResource: Resource<ResponseResult>?,
        viewModel: MovieViewModel
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when(movieResource) {
                is Resource.Success ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(
                            items = movieResource.data!!.results,
                            itemContent = {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${it.posterPath}",
                                    contentDescription = it.title,
                                    modifier = Modifier.clickable {
                                        viewModel.selectedMovie = it
                                        navController.navigate(MovieLibraryScreens.MovieDetailsScreen.name)
                                    }
                                )
                            }
                        )
                    }
                is Resource.Error ->
                    Text(
                        text = movieResource.message!!,
                        fontSize = 30.sp,
                        color = Color.Red
                    )
                is Resource.Empty ->
                    Text(
                        text = "Search for a movie!",
                        fontSize = 30.sp,
                        color = Color.White
                    )
                is Resource.Loading ->
                    Text(
                        text = "Loading...",
                        fontSize = 30.sp,
                        color = Color.White
                    )
                else ->
                    Text(
                        text = "Something went wrong...",
                        fontSize = 30.sp,
                        color = Color.White
                    )
            }
        }
    }
}