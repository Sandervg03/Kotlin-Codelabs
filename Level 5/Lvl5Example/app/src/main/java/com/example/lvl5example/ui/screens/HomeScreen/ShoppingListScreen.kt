package com.example.lvl5example.ui.screens.HomeScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lvl5example.R
import com.example.lvl5example.data.ShoppingListItem
import com.example.lvl5example.viewmodel.ShoppingListViewModel

class ShoppingListScreen {

    @Composable
    fun Create(
        navController: NavHostController,
        viewModel: ShoppingListViewModel = viewModel()
    ) {
        val context = LocalContext.current

        val shoppingListItems: List<ShoppingListItem>? by viewModel.shoppingListItems.observeAsState()

        val showDialogState: MutableState<Boolean> = remember {
            mutableStateOf(false)
        }

        val onDeleteShoppingListItem = { shoppingListItem: ShoppingListItem ->
            viewModel.removeShoppingListItem(shoppingListItem)
        }

        val onDeleteAllShoppingListItems = {
            viewModel.removeAllShoppingListitems()
        }

        val onAddShoppingListItem = { shoppingListItem: ShoppingListItem ->
            viewModel.addShoppingListItem(shoppingListItem)
        }

        Column(
            modifier = Modifier.fillMaxHeight()
        ) {

            if (showDialogState.value) {
                AddShoppingListDialog(
                    context = context,
                    showDialogState = showDialogState,
                    onAddShoppingListItem = onAddShoppingListItem,
                )
            }

            ShoppingList(
                context = context,
                modifier = Modifier.weight(1f),
                shoppingListItems = shoppingListItems ?: arrayListOf(),
                onDeleteShoppingListItem = onDeleteShoppingListItem
            )
            AddDeleteButtons(
                dialogStage = showDialogState,
                onDeleteAllShoppingListItems
            )
        }
    }

    @Composable
    private fun ShoppingList(
        context: Context,
        modifier: Modifier,
        shoppingListItems: List<ShoppingListItem>,
        onDeleteShoppingListItem: (ShoppingListItem) -> Unit,
    ) {
        LazyColumn{
            items(shoppingListItems) { shoppingListItem ->
                Row(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    Toast
                                        .makeText(
                                            context,
                                            R.string.press_long_to_delete,
                                            Toast.LENGTH_LONG
                                        )
                                        .show()
                                },
                                onLongPress = { onDeleteShoppingListItem(shoppingListItem) }
                            )
                        }
                ) {
                    Text(
                        text = "${shoppingListItem.amount}X",
                        modifier = Modifier.padding(end = 24.dp)
                    )
                    Text(text = shoppingListItem.product)
                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier.alpha(0.6f),
                    thickness = 1.dp
                )
            }
        }
    }

    @Composable
    private fun AddDeleteButtons(
        dialogStage: MutableState<Boolean>,
        onDeleteAllShoppingListItems: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            FloatingActionButton(
                onClick = {
                    dialogStage.value = true
                },
                containerColor = Color.Green
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add", tint = Color.White)
            }

            FloatingActionButton(
                onClick = {
                    onDeleteAllShoppingListItems()
                          },
                containerColor = Color.Red
            ) {
                Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete", tint = Color.White)
            }
        }
    }

    @Composable
    private fun AddShoppingListDialog(
        context: Context,
        showDialogState: MutableState<Boolean>,
        onAddShoppingListItem: (ShoppingListItem) -> Unit
    ) {
        val (shoppingListItemProduct, setShoppingListItemProduct) = remember {
            mutableStateOf("")
        }
        val (shoppingListItemAmount, setShoppingListItemAmount) = remember {
            mutableStateOf("")
        }

        Dialog(
            onDismissRequest = {
                showDialogState.value = false
            },
            DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.add_product_to_list),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        value = shoppingListItemProduct,
                        placeholder = {
                            Text(text = stringResource(id = R.string.add_product))},
                        onValueChange = {
                            setShoppingListItemProduct(it)
                        }
                    )
                    OutlinedTextField(
                        value = shoppingListItemAmount,
                        placeholder = {
                            Text(text = stringResource(id = R.string.amount))
                        },
                        onValueChange = {
                            setShoppingListItemAmount(it)
                                        },
                        )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.padding(top = 16.dp),
                            onClick = {
                                if (shoppingListItemAmount.isDigitsOnly()) {
                                    if (shoppingListItemProduct.isNotEmpty()
                                    && shoppingListItemAmount.isNotEmpty()
                                    ) {
                                    val shoppingListItem =
                                        ShoppingListItem(shoppingListItemAmount.toInt(), shoppingListItemProduct)
                                    onAddShoppingListItem(shoppingListItem)
                                    showDialogState.value = false
                                    setShoppingListItemProduct("")
                                    setShoppingListItemAmount("")
                                } else {
                                    Toast.makeText(
                                        context,
                                        R.string.empty_fields_err,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Amount can only be numbers",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }                                }
                        ) {
                            Text(
                                text = stringResource(id = R.string.okay)
                            )
                        }
                    }
                }
            }
        }
    }
}