package com.example.lvl4task1.ui.screens.catsScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lvl4task1.data.api.Api
import com.example.lvl4task1.data.api.util.Resource
import com.example.lvl4task1.data.model.Cat
import com.example.lvl4task1.viewmodel.CatsViewModel.CatsViewModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CatsScreen(viewModel: CatsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val catResource: Resource<Cat>? by viewModel.catResource.observeAsState()

    val catText: String = when (catResource) {
        is Resource.Success ->
            "Picture taken at ${getPictureDate(catResource?.data?.creationDate.toString())}"
        is Resource.Error -> catResource?.message.toString()
        is Resource.Loading -> catResource?.message.toString()
        is Resource.Empty -> "Nothing to see... Click the 'Get new cat' button!"
        else -> "Something went wrong..."
    }

    val catImage = Api.CATS_BASE_URL + "cat/" + catResource?.data?.urlExtension;

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cat image and image capture date",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = catText,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(16.dp))
        AsyncImage(
            model = catImage,
            contentDescription = "Cat",
            modifier = Modifier.fillMaxHeight(0.5f)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { viewModel.getCat() }) {
            Text(
                text = "Get new cat",
                fontSize = 20.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getPictureDate(date: String): String {
    val zonedDateTime = ZonedDateTime.parse(date)
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)
    return zonedDateTime.format(formatter)
}