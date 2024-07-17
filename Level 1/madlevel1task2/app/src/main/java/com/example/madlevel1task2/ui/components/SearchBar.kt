package com.example.madlevel1task2.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun SearchBar(modifier: Modifier, onSearchChange: (String) -> Unit) {
    var textFieldValue by remember {
        mutableStateOf("")
    }
    TextField(
        value = textFieldValue,
        placeholder = { Text(text = "Search a place!") },
        onValueChange = {
            textFieldValue = it
            onSearchChange(textFieldValue)
                        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        shape = RectangleShape
    )
}