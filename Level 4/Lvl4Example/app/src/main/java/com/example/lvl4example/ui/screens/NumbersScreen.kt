package com.example.lvl4example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lvl4example.R
import com.example.lvl4example.data.api.NumbersApi
import com.example.lvl4example.data.api.util.Resource
import com.example.lvl4example.data.model.RandomNumber
import com.example.lvl4example.viewmodel.NumbersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumbersScreen(navController: NavHostController, viewModel: NumbersViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ))
        },
        modifier = Modifier.fillMaxSize()
    ) {
        innerPadding ->
        ScreenContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            getNewNumber = {
                viewModel.getNumber()
            }
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier,
    viewModel: NumbersViewModel,
    getNewNumber: () -> Unit
) {

    val numberResource: Resource<RandomNumber>? by viewModel.numberResource.observeAsState()

    val numberText = when(numberResource) {
        is Resource.Success -> numberResource?.data?.text
        is Resource.Error -> numberResource?.message
        is Resource.Loading -> stringResource(id = R.string.loading_text)
        is Resource.Empty -> stringResource(id = R.string.empty_number_placeholder)
        else -> stringResource(id = R.string.something_wrong_state)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = NumbersApi.numberType.replaceFirstChar { it.uppercase() },
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(vertical = 60.dp, horizontal = 16.dp)
        )
        Text(
            text = numberText!!,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 8.dp)
                .heightIn(min = 96.dp)
        )

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.get_new_number)) },
            onClick = { getNewNumber() },
            icon = { Icon(Icons.Filled.Refresh, "Get new number") },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .width(150.dp)
        )
    }
}