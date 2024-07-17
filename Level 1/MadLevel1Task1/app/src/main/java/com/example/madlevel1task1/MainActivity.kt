package com.example.madlevel1task1

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.madlevel1task1.model.Reminder
import com.example.madlevel1task1.ui.theme.MadLevel1Task1Theme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MadLevel1Task1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun MainScreen(modifier: Modifier = Modifier) {
        Scaffold (
            topBar = { TopBar(modifier) },

            content = { padding -> MainContent(Modifier.padding(padding)) }
        )
    }

    @Composable
    private fun MainContent(modifier: Modifier) {
        val reminders = remember {
            mutableStateListOf<Reminder>()
        }

        Column(
            modifier
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            InputContent(
                modifier,
                onAddReminder = {
                    reminderText -> reminders.add(Reminder(reminderText))
                })
            LazyColumnContent(reminders = reminders)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopBar(modifier: Modifier = Modifier) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            modifier = modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.blue),
                titleContentColor = colorResource(id = R.color.white)
            )
        )
    }

    @Composable
    private fun InputContent(modifier: Modifier, onAddReminder: (String) -> Unit) {
        val context = LocalContext.current

        var reminderText by remember { mutableStateOf("") }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = reminderText,
                placeholder = { Text(text = stringResource(id = R.string.new_reminder))},
                onValueChange = {
                    reminderText = it
                },
                label = { Text(text = stringResource(id = R.string.enter_new_reminder))}
            )
            Spacer(modifier = modifier.width(8.dp))
            Button(
                onClick = {
                    if (reminderText != "") {
                        onAddReminder(reminderText)
                    } else {
                        Toast.makeText(context, "Please enter a reminder.", Toast.LENGTH_LONG).show()
                    }
                }
            ) {
                Icon(Icons.Filled.Send, "Process user input.")
            }
        }
    }

    @Composable
    private fun LazyColumnContent(reminders: List<Reminder>) {
        LazyColumn {
            items(items = reminders, itemContent = { reminder ->
                Text(
                    text = reminder.reminderData,
                    Modifier.padding(16.dp)
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.alpha(0.5f),
                    thickness = 1.dp
                )
            })
        }
    }
}
