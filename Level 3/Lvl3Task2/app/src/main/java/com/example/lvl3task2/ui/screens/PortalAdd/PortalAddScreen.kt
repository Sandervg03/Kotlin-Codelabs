package com.example.lvl3task2.ui.screens.PortalAdd

import android.content.Context
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lvl3task2.R
import com.example.lvl3task2.ViewModels.PortalViewModel
import com.example.lvl3task2.ui.screens.PortalOverview.validation.validateInputs

@Composable
fun PortalAddScreen(navController: NavHostController, viewModel: PortalViewModel) {

    var context: Context = LocalContext.current

    Scaffold(
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var portalName by remember {
                    mutableStateOf(String())
                }
                OutlinedTextField(
                    label = {
                        Text(text = stringResource(id = R.string.portal_name))
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.portal_name))
                    },
                    value = portalName,
                    onValueChange = {
                        portalName = it
                    }
                )

                Spacer(modifier = Modifier.padding(16.dp))

                var portalUrl by remember {
                    mutableStateOf("https://")
                }
                OutlinedTextField(
                    label = { 
                        Text(text = stringResource(id = R.string.portal_url))
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.portal_url))
                    },
                    value = portalUrl,
                    onValueChange = {
                        portalUrl = it
                    }
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            if (validateInputs(
                                    name = portalName,
                                    url = portalUrl,
                                    context = context
                                )
                            ) {
                                viewModel.addPortalName(portalName)
                                viewModel.addPortalUrl(portalUrl)
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Add portal")
                    }
                }
            }
        }
    )
}