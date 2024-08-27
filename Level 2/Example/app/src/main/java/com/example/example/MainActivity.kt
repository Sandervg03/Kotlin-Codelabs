package com.example.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.example.logistic.diceValueToImageId
import com.example.example.logistic.getRandomNumber
import com.example.example.ui.components.diceToDisplay
import com.example.example.ui.components.instruction
import com.example.example.ui.components.nextRollButton
import com.example.example.ui.components.topAppBar
import com.example.example.ui.components.totalDiceRollsValue
import com.example.example.ui.theme.ExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        topAppBar(stringResource(id = R.string.app_name))
                    }
                ) { innerPadding ->
                    ScreenContent(Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun ScreenContent(modifier: Modifier) {
        var currentDiceValue: Int by remember {
            mutableIntStateOf(getRandomNumber())
        }
        var amountOfDiceRolled: Int by remember {
            mutableIntStateOf(1)
        }
        var totalOfRolledDiceValues: Int by remember {
            mutableIntStateOf(currentDiceValue)
        }
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            instruction()
            diceToDisplay(diceValueToImageId(currentDiceValue))
            totalDiceRollsValue(amountOfDiceRolled, totalOfRolledDiceValues)
            nextRollButton(onClickFunction = {
                currentDiceValue = getRandomNumber()
                amountOfDiceRolled+= 1
                totalOfRolledDiceValues += currentDiceValue
            })
        }
    }
}