package com.example.example.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.example.R
import com.example.example.logistic.getRandomNumber

@Composable
fun nextRollButton(onClickFunction: () -> Unit) {
    Button(
        onClick = onClickFunction,
        modifier = Modifier
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF87CEEB))
    ) {
        Text(text = stringResource(R.string.next_roll))
    }
}