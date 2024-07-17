package com.example.madlevel1task2.logistics.searchbar

import com.example.madlevel1task2.logistics.models.Place

fun FilterLocation(places: List<Place>, location: String): List<Place> {

    if (location.isEmpty()) {
        return places
    } else {
        return places.filter { place ->
            place.city.contains(location, ignoreCase = true) ||
                    place.location.contains(location, ignoreCase = true)
        }
    }
}