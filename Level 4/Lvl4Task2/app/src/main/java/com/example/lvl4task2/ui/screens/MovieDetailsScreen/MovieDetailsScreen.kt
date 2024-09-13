package com.example.lvl4task2.ui.screens.MovieDetailsScreen

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${viewModel.selectedMovie?.backdropPath}",
                contentDescription = viewModel.selectedMovie?.title,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/${viewModel.selectedMovie?.posterPath}",
                    contentDescription = viewModel.selectedMovie?.title,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "${viewModel.selectedMovie?.title}",
                        fontSize = 30.sp
                    )
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                    ) {
                        Icon(imageVector = Icons.Rounded.Star, contentDescription = "Rating")
                        Text(
                            text = "${viewModel.selectedMovie?.voteAverage}",
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Column {
                Text(
                    text = "Overview",
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 10.dp)
                )
                Text(
                    text = "${viewModel.selectedMovie?.overview}",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}