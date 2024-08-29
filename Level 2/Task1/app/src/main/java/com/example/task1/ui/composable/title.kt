package com.example.task1.ui.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Title(title: String, modifier: Modifier, fontSize: Int) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = fontSize.sp
    )
}
