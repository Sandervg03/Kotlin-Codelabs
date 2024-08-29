package com.example.task1.ui.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Paragraph(text: String, modifier: Modifier, fontSize: Int){
    Text(text = text, modifier = modifier, fontSize = fontSize.sp)
}