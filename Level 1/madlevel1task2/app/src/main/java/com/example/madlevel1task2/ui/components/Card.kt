package com.example.madlevel1task2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Card(city: String, location: String, image: Int) {
    Column {
        Image(painter = painterResource(id = image), contentDescription = location)
        Text(text = location, color = Color.LightGray)
        Text(text = city, color = Color.DarkGray)
        Spacer(modifier = Modifier.padding(0.dp, 10.dp))
    }
}