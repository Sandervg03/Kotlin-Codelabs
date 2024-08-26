package com.example.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun diceToDisplay(dice: Int) {
    Image(
        painter = painterResource(dice),
        contentDescription = "Dice",
        modifier = Modifier
            .padding(all = 64.dp)
            .width(250.dp)
            .height(250.dp)
        )
}