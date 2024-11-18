package com.example.shoppinglistcompose.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglistcompose.R
import com.example.shoppinglistcompose.dialog.MainDialog
import com.example.shoppinglistcompose.navigation.NavigationGraph
import com.example.shoppinglistcompose.ui.theme.BlueLight
import com.example.shoppinglistcompose.utils.Routes
import com.example.shoppinglistcompose.utils.UiEvent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainNavHostController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ uiEvent ->
            when(uiEvent){
                is UiEvent.NavigateMain -> {
                    mainNavHostController.navigate(uiEvent.route)
                }
                is UiEvent.Navigate -> {
                    navController.navigate(uiEvent.route)
                }
                else -> {}
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNav(currentRoute){ route ->
                viewModel.onEvent(MainScreenEvent.Navigate(route))
            }
        },
        floatingActionButton = {
            if (viewModel.showFloatingButton.value) FloatingActionButton(
                onClick = {
                    viewModel.onEvent(MainScreenEvent.OnNewItemClick(currentRoute ?: Routes.SHOPPING_LIST))
                },
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp, bottomStart = 30.dp
                ),
                containerColor = BlueLight
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.add_icon,
                    ),
                    contentDescription = "Add",
                    tint = Color.White

                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,


        ) {
        NavigationGraph(navController){ route ->
            viewModel.onEvent(MainScreenEvent.NavigateMain(route))
        }
        MainDialog(viewModel)
    }
}