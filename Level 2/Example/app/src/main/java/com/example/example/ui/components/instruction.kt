package com.example.example.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.example.R

@Composable
fun instruction() {
    Text(
        text = stringResource(id = R.string.instruction),
        fontSize = 22.sp
    )
}