package com.example.madlevel1example

import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.madlevel1example.ui.theme.MadLevel1ExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MadLevel1ExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
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
        content = { padding -> ScreenContent(Modifier.padding(padding))}
    )
}


@Composable
fun ScreenContent(modifier: Modifier) {

    Column(
        modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.animal_question),
            style = MaterialTheme.typography.headlineSmall,
            )
        Image(
            painter = painterResource(id = R.drawable.giraffe), 
            contentDescription = "Giraffe",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )
        InputSegment(modifier)
    }
}


@Composable
fun InputSegment(modifier: Modifier) {

    val context = LocalContext.current

    var answerText by remember {
        mutableStateOf(String())
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = answerText,
            placeholder = { Text(text = stringResource(id = R.string.animal_question))},
            onValueChange = {
                answerText = it
            },
            label = { Text(text = stringResource(id = R.string.answer_label))}
        )
        Spacer(modifier = modifier.width(8.dp))
        Button(onClick = {
            verifyAnswer(context, answerText)
        }) {
            Icon(Icons.Filled.Send, "Process user input.")
        }
    }
}


fun verifyAnswer(context: Context, answerText: String) {
    var toastText = "\"" + answerText + "\""
    toastText += if (answerText.uppercase() == context.getString(R.string.giraffe_upper)) {
        context.getString(R.string.correct)
    } else {
        context.getString(R.string.incorrect)
    }
    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
}