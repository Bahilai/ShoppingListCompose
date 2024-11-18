package com.example.shoppinglistcompose.note_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppinglistcompose.dialog.MainDialog
import com.example.shoppinglistcompose.ui.theme.BlueLight
import com.example.shoppinglistcompose.ui.theme.EmptyText
import com.example.shoppinglistcompose.ui.theme.Red
import com.example.shoppinglistcompose.utils.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val scaffoldState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEven ->
            when (uiEven) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEven.route)
                }

                is UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.showSnackbar(
                        uiEven.message,
                        "Undone"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NoteListEvent.UnDoneDeleteItem)
                    }
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
                modifier = Modifier.padding(bottom = 60.dp)//.background(Red)
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(15.dp),
            ) {
                TextField(
                    value = viewModel.searchText,
                    onValueChange = { text ->
                        viewModel.onEvent(NoteListEvent.OnTextSearchChange(text))
                    },
                    label = {
                        Text(text = "Search...")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = BlueLight
                    )
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                items(viewModel.noteList) { item ->
                    UiNoteItem(viewModel.titleColor.value, item) { event ->
                        viewModel.onEvent(event)
                    }
                }
            }
            MainDialog(viewModel)
            if (viewModel.noteList.isEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(),
                    text = "Empty",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    color = EmptyText
                )
            }
        }
    }
}