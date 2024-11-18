package com.example.shoppinglistcompose.add_item_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppinglistcompose.R
import com.example.shoppinglistcompose.dialog.MainDialog
import com.example.shoppinglistcompose.ui.theme.BlueLight
import com.example.shoppinglistcompose.ui.theme.DarkText
import com.example.shoppinglistcompose.ui.theme.GrayLight
import com.example.shoppinglistcompose.ui.theme.Red
import com.example.shoppinglistcompose.utils.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val scaffoldState = remember { SnackbarHostState() }
    val itemsList = viewModel.itemsList?.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ uiEvent ->
            when(uiEvent){
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.showSnackbar(
                        uiEvent.message
                    )
                }
                else -> {}
            }
        }
    }
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = scaffoldState) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = Red,
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.itemText.value,
                        onValueChange = {text ->
                            viewModel.onEvent(AddItemEvent.OnTextChange(text))
                        },
                        label = {
                            Text(
                                text = "New item",
                                fontSize = 12.sp
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = BlueLight,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = DarkText
                        ),
                        singleLine = true
                    )
                    IconButton(
                        onClick = {
                            viewModel.onEvent(AddItemEvent.OnItemSave)
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.simple_add_icon
                            ),
                            contentDescription = "Add"
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 5.dp,
                        end = 5.dp
                    )
            ) {
                if(itemsList != null){
                    items(itemsList.value){item ->
                        UiAddItem(item = item, onEvent = {event ->
                            viewModel.onEvent(event)
                        })
                    }
                }
            }
        }
        MainDialog(viewModel)
        if(itemsList?.value?.isEmpty() == true){
            /* Image(
                 painter = painterResource(id = R.drawable.danil_image),
                 contentDescription = "Centered Image",
                 modifier = Modifier
                     .fillMaxSize()
                     .wrapContentSize(Alignment.Center)
                     .size(500.dp),
                 contentScale = ContentScale.Crop
             )*/
                 Text(
                 modifier = Modifier
                     .fillMaxSize()
                     .wrapContentHeight(),
                 text = "Empty",
                 fontSize = 25.sp,
                 textAlign = TextAlign.Center
             )
        }
    }
}