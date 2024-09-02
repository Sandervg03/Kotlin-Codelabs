package com.example.lvl3task2.ui.screens.PortalOverview

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lvl3task2.AppScreens
import com.example.lvl3task2.ViewModels.PortalViewModel
import com.example.lvl3task2.ui.screens.PortalOverview.composables.Card

@Composable
fun PortalOverviewScreen(navController: NavHostController, viewModel: PortalViewModel) {

    var context: Context = LocalContext.current

    Scaffold(
        content = { innerPadding ->
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(viewModel.getPortalNames()) { index, portal ->
                    Card(name = portal, url = viewModel.getPortalUrls()[index], context = context)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppScreens.PortalAddScreen.name)
                },
                containerColor = Color.Blue,
                contentColor = Color.White
            ) {
                Text(text = "+", fontSize = 20.sp)
            }
        }
    )
}