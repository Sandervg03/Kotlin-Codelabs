package com.example.example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.example.R
import com.example.example.viewmodel.RemindersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersListScreen(navController: NavHostController, viewModel: RemindersViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = {innerPadding ->
            ScreenContent(
                modifier = Modifier.padding(innerPadding),
                reminders = viewModel.getReminders()
            )},
        floatingActionButton = { RemindersListScreenFab(navController = navController)}
    )
}

@Composable
private fun ScreenContent(modifier: Modifier, reminders: List<String>) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Headers()
        RemindersList(reminders = reminders)
    }
}

@Composable
private fun Headers() {
    Text(
        text = stringResource(id = R.string.reminders),
        modifier = Modifier.padding(bottom = 8.dp),
        style = MaterialTheme.typography.headlineMedium
    )

    Text(
        text = stringResource(id = R.string.see_below),
        modifier = Modifier.padding(bottom = 16.dp),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun RemindersList(reminders: List<String>) {
    LazyColumn {
        items(items = reminders, itemContent = { item: String ->
            ReminderCard(item)
        })
    }
}

@Composable
private fun ReminderCard(item: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Text(
            text = item,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun RemindersListScreenFab(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(RemindersScreens.NewReminder.name)
        }) {
        Text(text = "New")
    }
}