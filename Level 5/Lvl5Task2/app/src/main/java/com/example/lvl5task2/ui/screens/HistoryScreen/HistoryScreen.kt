package com.example.lvl5task2.ui.screens.HistoryScreen

import android.icu.lang.UCharacter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import com.example.lvl5task2.R
import com.example.lvl5task2.data.Game
import com.example.lvl5task2.data.converter.Converters
import com.example.lvl5task2.ui.screens.GameScreen.GameScreen
import com.example.lvl5task2.viewmodel.GameViewModel

class HistoryScreen {

    @Composable
    fun Create(viewModel: GameViewModel) {
        val games: List<Game>? = viewModel.games.observeAsState().value
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    if (!games.isNullOrEmpty()) viewModel.deleteGames() }
                ) {
                    Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete")
                }
            }
        ) { innerPadding ->
            if (!games.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(games.size, itemContent = {
                        game -> 
                        Spacer(modifier = Modifier.padding(10.dp))
                        Column (
                            modifier = Modifier.fillMaxWidth(), 
                            verticalArrangement = Arrangement.Center, 
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = GameScreen.getResultString(games[game].result),
                                style = MaterialTheme.typography.headlineLarge
                            )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = games[game].date.toString(),
                            style = MaterialTheme.typography.titleMedium
                            )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = GameScreen.getDrawable(games[game].computer)
                                    ),
                                    contentDescription = games[game].computer
                                )
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(text = "Computer")
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = GameScreen.getDrawable(games[game].player)
                                    ),
                                    contentDescription = games[game].player
                                )
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(text = "You")
                            }
                        }
                            Spacer(modifier = Modifier.padding(10.dp))
                            HorizontalDivider()
                        }
                    })
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Nothing to see yet...",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
    }
}