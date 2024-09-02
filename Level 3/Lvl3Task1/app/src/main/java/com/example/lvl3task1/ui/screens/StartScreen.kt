package com.example.lvl3task1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lvl3task1.GameRatingScreensEnum
import com.example.lvl3task1.R
import com.example.lvl3task1.viewModel.GameViewModel

@Composable
fun StartScreen(navController: NavHostController, viewModel: GameViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            fontSize = 30.sp,
            lineHeight = 35.sp
            )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = stringResource(id = R.string.click_start),
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                viewModel.randomlyAssessableGame()
                navController.navigate(GameRatingScreensEnum.RatingScreen.name)
            }) {
            Text(
                text = stringResource(id = R.string.start)
            )
        }
    }
}