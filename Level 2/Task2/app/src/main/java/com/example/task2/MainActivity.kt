package com.example.task2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task2.dataClass.Conjunction
import com.example.task2.logistic.getConjunctions
import com.example.task2.logistic.processConjunctions
import com.example.task2.ui.theme.Task2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task2Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.app_name))
                                    },
                            colors = (TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Blue,
                                titleContentColor = Color.White
                            ))
                        )
                    },
                    modifier = Modifier,
                    content = { innerPadding ->
                        ScreenContent(Modifier.padding(innerPadding))
                    })
                }
            }
        }
    }

@Composable
fun ScreenContent(modifier: Modifier) {

    val context = LocalContext.current
    val userAnswers = remember {
        mutableStateListOf<String>()
    }
    val conjunctions: List<Conjunction> = getConjunctions()
    conjunctions.forEach{ _ -> userAnswers.add("")}

    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProvideTextStyle(value = TextStyle(fontSize = 15.sp)) {
                Text(text = "A")
                Text(text = "B")
                Text(text = "A&B")
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            itemsIndexed(conjunctions) { index, conjunction ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = conjunction.argument1)
                    Text(text = conjunction.argument2)
                    Column {
                        Row {
                            RadioButton(
                                selected = userAnswers[index] == "T",
                                onClick = {
                                    userAnswers[index] = "T"
                                })
                            Text(text = "T")
                        }
                        Row {
                            RadioButton(
                                selected = userAnswers[index] == "F",
                                onClick = {
                                    userAnswers[index] = "F"
                                })
                            Text(text = "F")
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    val correctAnswers: Int = processConjunctions(conjunctions, userAnswers.toList())
                    Toast.makeText(
                        context,
                        "You have answered ${correctAnswers} conjunctions correctly",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(text = "Process")
            }
            Button(
                onClick = {
                    userAnswers.forEachIndexed{index, answer ->
                        userAnswers[index] = ""
                    }
                    Toast.makeText(
                        context,
                        "Your answers have been reset",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(text = "Reset")
            }
        }
    }
}