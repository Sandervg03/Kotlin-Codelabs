package com.example.lvl4task1.ui.screens.dogsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import coil.compose.AsyncImage
import com.example.lvl4task1.data.api.Api
import com.example.lvl4task1.data.api.util.Resource
import com.example.lvl4task1.data.model.Dog
import com.example.lvl4task1.viewmodel.DogsViewModel.DogsViewModel

@Composable
fun DogsScreen(viewModel: DogsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val dogsResource: Resource<Dog>? by viewModel.dogsResource.observeAsState()

    val dogText = when(dogsResource){
        is Resource.Success -> getDogBreed(dogsResource?.data?.randomDogPictureUrl.toString())
        is Resource.Error -> dogsResource?.message.toString()
        is Resource.Loading -> dogsResource?.message.toString()
        is Resource.Empty -> "Nothing to see... Click the 'Get new dog' button!"
        else -> "Something went wrong"
    }

    val dogImage = dogsResource?.data?.randomDogPictureUrl

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Dog breed and image",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = dogText,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(16.dp))
        AsyncImage(
            model = dogImage,
            contentDescription = "Dog",
            modifier = Modifier.fillMaxHeight(0.5f)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { viewModel.getDog() }) {
            Text(
                text = "Get new dog",
                fontSize = 20.sp
                )
        }
    }
}

private fun getDogBreed(url: String): String {
    val url: List<String> = url.split("/");
    return url[4]
}