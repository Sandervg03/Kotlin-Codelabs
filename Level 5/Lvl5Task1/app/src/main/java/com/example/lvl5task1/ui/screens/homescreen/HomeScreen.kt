package com.example.lvl5task1.ui.screens.homescreen

import android.content.Context
import android.content.res.Resources.Theme
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lvl5task1.R
import com.example.lvl5task1.data.model.Game
import com.example.lvl5task1.ui.screens.Screens
import com.example.lvl5task1.util.Util
import com.example.lvl5task1.viewmodel.GameViewModel
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants

class HomeScreen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Create(
        navController: NavHostController,
        viewModel: GameViewModel
    ) {
        val context: Context = LocalContext.current
        val gamesBackLog: LiveData<List<Game>> = viewModel.gameBackLog
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        var deletedBackLog: Boolean by remember { mutableStateOf(false) }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            containerColor = Color.DarkGray,
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = stringResource(id = R.string.app_name), color = Color.White)
                            IconButton(onClick = {
                                deletedBackLog = true
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = context.getString(R.string.snackbar_msg),
                                        actionLabel = context.getString(R.string.undo),
                                        duration = SnackbarDuration.Short
                                    )
                                    if (result != SnackbarResult.ActionPerformed) {
                                        viewModel.deleteAllGames()
                                    }
                                    deletedBackLog = false
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete all games",
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate(Screens.AddScreen.route) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            },
            content = { innerPadding ->
                Modifier.padding(innerPadding)

                if (!deletedBackLog) {
                    Games(
                        context = context,
                        gamesBackLog,
                        modifier = Modifier.padding(16.dp),
                        snackbarHostState
                    )
                }
            }
        )
    }

    @Composable
    fun Games(
        context: Context,
        games: LiveData<List<Game>>,
        modifier: Modifier,
        snackbarHostState: SnackbarHostState
    ) {
        val gamesState by games.observeAsState()

        LazyColumn(
            modifier = modifier
                .padding(top = 80.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            gamesState?.sortedBy { it.release }.let { games ->
                if (games != null) {
                    itemsIndexed(
                        items = games,
                        key = { _, game -> game.hashCode() }
                    ) { _, game ->
                        GameCard(context, game = game, snackbarHostState = snackbarHostState)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun GameCard(
        context: Context,
        game: Game,
        viewModel: GameViewModel = viewModel(),
        snackbarHostState: SnackbarHostState,
    ) {
        val dismissState = rememberDismissState()
        if (dismissState.isDismissed(DismissDirection.StartToEnd) || dismissState.isDismissed(
                DismissDirection.EndToStart
            )
        ) {
            LaunchedEffect(Unit) {
                val result = snackbarHostState.showSnackbar(
                    message = context.getString(R.string.deleted_game, game.title),
                    actionLabel = context.getString(R.string.undo),
                    duration = SnackbarDuration.Short
                )
                if (result != SnackbarResult.ActionPerformed) {
                    viewModel.deleteGame(game)
                } else {
                    dismissState.reset()
                }
            }
        }

        SwipeToDismiss(
            state = dismissState,
            background = {},
            dismissContent = {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = game.title, style = MaterialTheme.typography.headlineSmall,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = game.platform)
                            Text(text = "Release: " + Util.dateToString(game.release))
                        }
                    }
                }
            },
            directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
        )
    }
}