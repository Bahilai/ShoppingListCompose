package com.example.shoppinglistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.shoppinglistcompose.main_screen.MainScreen
import com.example.shoppinglistcompose.navigation.MainNavigationGraph
import com.example.shoppinglistcompose.ui.theme.ShoppingListComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListComposeTheme {
                MainNavigationGraph()
            }
        }
    }
}
