package com.example.madlevel1task2.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madlevel1task2.logistics.models.Place
import com.example.madlevel1task2.logistics.places.generatePlaces

@Composable
fun MainDisplay(modifier: Modifier, places: List<Place>) {
    LazyVerticalGrid(
        modifier = modifier.padding(20.dp, 70.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(places) { place ->
            Card(city = place.city, location = place.location, image = place.image)
        }
    }
}