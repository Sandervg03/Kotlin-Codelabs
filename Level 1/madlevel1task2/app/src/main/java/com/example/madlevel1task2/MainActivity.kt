package com.example.madlevel1task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.madlevel1task2.logistics.models.Place
import com.example.madlevel1task2.logistics.places.generatePlaces
import com.example.madlevel1task2.logistics.searchbar.FilterLocation
import com.example.madlevel1task2.ui.components.MainDisplay
import com.example.madlevel1task2.ui.components.SearchBar
import com.example.madlevel1task2.ui.theme.Madlevel1task2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Madlevel1task2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier) {
    var searchQuery by remember { mutableStateOf("") }
    var places: List<Place> = generatePlaces()
    var filteredPlaces = FilterLocation(places, searchQuery)

    SearchBar(modifier = modifier, onSearchChange = {searchQuery = it})
    MainDisplay(modifier = modifier, places = filteredPlaces)
}
