package com.example.lvl4task2.ui.screens.MovieDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lvl4task2.viewmodel.MovieViewModel

class MovieDetailsScreen {

    @Composable
    fun Create(navController: NavHostController, viewModel: MovieViewModel) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${viewModel.selectedMovie.backdropPath}",
                contentDescription = viewModel.selectedMovie.title,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/${viewModel.selectedMovie.backdropPath}",
                    contentDescription = viewModel.selectedMovie.title,
                    modifier = Modifier
                        .height(80.dp)
                        .padding(20.dp)
                        .fillMaxWidth(0.3f)
                )
                Column {
                    viewModel.selectedMovie.title?.let {
                        Text(
                            text = it,
                            fontSize = 30.sp
                        )
                    }
                    viewModel.selectedMovie.overview?.let {
                        Text(
                            text = it,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}