package com.example.task1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import com.example.task1.logistic.QuestionAnswer
import com.example.task1.logistic.getQuestions
import com.example.task1.ui.composable.Paragraph
import com.example.task1.ui.composable.Title
import com.example.task1.ui.theme.Task1Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task1Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.app_name))
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Blue,
                                titleContentColor = Color.White
                            )
                        )},
                    content = { paddingValues ->
                        ScreenContent(paddingValues) 
                    })
            }
        }
    }
}

@Composable
fun ScreenContent(paddingValues: PaddingValues) {

    var questionRemember = remember {
        mutableStateListOf<QuestionAnswer>()
    }

    getQuestions().forEach{question -> questionRemember.add(question)}

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(paddingValues)
            .padding(0.dp, 10.dp)
    ) {
        Title(title = stringResource(id = R.string.title), modifier = Modifier.padding(5.dp), fontSize = 30)
        Spacer(modifier = Modifier.padding(0.dp, 5.dp))
        Paragraph(text = stringResource(id = R.string.instruction), modifier = Modifier.padding(5.dp), fontSize = 15)
        Spacer(modifier = Modifier.padding(0.dp, 10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
        )
            {
            items(questionRemember) { question ->
                val answer: Boolean = question.answer
                Paragraph(text = question.question, modifier = Modifier.padding(5.dp), fontSize = 15)
                Row(horizontalArrangement = Arrangement.SpaceAround) {
                    Button(
                        onClick = { if (question.answer) {
                            questionRemember.remove(question)
                            Toast.makeText(context, "You are right!",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "You are wrong...",Toast.LENGTH_SHORT).show()
                        } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Green,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "True")
                    }
                    Button(
                        onClick = { if (!question.answer) {
                            questionRemember.remove(question)
                            Toast.makeText(context, "You are right!",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "You are wrong...",Toast.LENGTH_SHORT).show()
                        } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "False")
                    }
                }
            }
        }
    }
}
