package com.example.lvl5task2.ui.screens.GameScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lvl5task2.R
import com.example.lvl5task2.data.Game
import com.example.lvl5task2.viewmodel.GameViewModel
import java.util.Date

class GameScreen {

    @Composable
    fun Create(viewModel: GameViewModel) {

        val winsCount = viewModel.winsCount.observeAsState()
        val lossCount = viewModel.lossCount.observeAsState()
        val drawCount = viewModel.drawCount.observeAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.title),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = stringResource(id = R.string.instructions),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = stringResource(id = R.string.history_title),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = stringResource(
                    id = R.string.history,
                    winsCount.value.toString(),
                    drawCount.value.toString(),
                    lossCount.value.toString()
                ),
                style = MaterialTheme.typography.bodyMedium
            )
            Game(viewModel)
        }
    }

    @Composable
    private fun Game(viewModel: GameViewModel) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(32.dp)
        ) {
            var result: String by remember {
                mutableStateOf("Nothing to see yet...")
            }
            var computerMove: Int? by remember {
                mutableStateOf(null)
            }
            var playerMove: Int? by remember {
                mutableStateOf(null)
            }

            Text(
                text = result,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.padding(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    if (computerMove != null) {
                        Card {
                            Image(
                                painter = painterResource(id = computerMove!!),
                                contentDescription = "Computer move",
                                modifier = Modifier.width(100.dp)
                            )
                        }
                        Text(text = "Computer")
                    }
                }

                Column {
                    if (playerMove != null) {
                        Card {
                            Image(
                                painter = painterResource(id = playerMove!!),
                                contentDescription = "Your move",
                                modifier = Modifier.width(100.dp)
                            )
                        }
                        Text(text = "You")
                    }
                }
            }
            Spacer(modifier = Modifier.padding(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    onClick = {
                        val gameResult: Game = getGameResult(getComputerMove(), "Rock")
                        computerMove = getDrawable(gameResult.computer)
                        playerMove = getDrawable(gameResult.player)
                        result = getResultString(result = gameResult.result)
                        viewModel.addGame(gameResult)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rock),
                        contentDescription = "Rock",
                        modifier = Modifier.width(70.dp)
                    )
                }
                Card(
                    onClick = {
                        val gameResult: Game = getGameResult(getComputerMove(), "Paper")
                        computerMove = getDrawable(gameResult.computer)
                        playerMove = getDrawable(gameResult.player)
                        result = getResultString(gameResult.result)
                        viewModel.addGame(gameResult)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.paper),
                        contentDescription = "Paper",
                        modifier = Modifier.width(70.dp)
                    )
                }
                Card(
                    onClick = {
                        val gameResult: Game = getGameResult(getComputerMove(), "Scissors")
                        computerMove = getDrawable(gameResult.computer)
                        playerMove = getDrawable(gameResult.player)
                        result = getResultString(gameResult.result)
                        viewModel.addGame(gameResult)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.scissors),
                        contentDescription = "Scissors",
                        modifier = Modifier.width(70.dp)
                    )
                }
            }
        }
    }

    private fun getComputerMove(): String {
        val moves: List<String> = listOf("Rock", "Paper", "Scissors")
        return moves.random()
    }

    private fun getGameResult(computer: String, user: String): Game {
        val result: Game = when (computer) {
            "Rock" -> {
                when(user) {
                    "Paper" -> Game(0, Date(), computer, user, "Win")
                    "Scissors" -> Game(0, Date(), computer, user, "Lose")
                    else -> Game(0, Date(), computer, user, "Draw")
                }
            }
            "Paper" -> {
                when(user) {
                    "Paper" -> Game(0, Date(), computer, user, "Draw")
                    "Scissors" -> Game(0, Date(), computer, user, "Win")
                    else -> Game(0, Date(), computer, user, "Lose")
                }
            }
            else -> {
                when(user) {
                    "Paper" -> Game(0, Date(), computer, user, "Lose")
                    "Scissors" -> Game(0, Date(), computer, user, "Draw")
                    else -> Game(0, Date(), computer, user, "Win")
                }
            }
        }
        return result
    }

    companion object {

        fun getResultString(result: String): String {
            return when(result) {
                "Win" -> "You win!"
                "Lose" -> "Computer wins!"
                else -> "Draw..."
            }
        }

        fun getDrawable(move: String): Int {
            return when(move) {
                "Rock" -> R.drawable.rock
                "Paper" -> R.drawable.paper
                else -> R.drawable.scissors
            }
        }
    }
}