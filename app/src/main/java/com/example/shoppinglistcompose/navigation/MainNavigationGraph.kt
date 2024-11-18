package com.example.shoppinglistcompose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shoppinglistcompose.about_screen.AboutScreen
import com.example.shoppinglistcompose.add_item_screen.AddItemScreen
import com.example.shoppinglistcompose.main_screen.MainScreen
import com.example.shoppinglistcompose.new_note_screen.NewNoteScreen
import com.example.shoppinglistcompose.note_list_screen.NoteListScreen
import com.example.shoppinglistcompose.settings_screen.SettingsScreen
import com.example.shoppinglistcompose.shopping_list_screen.ShoppingListScreen
import com.example.shoppinglistcompose.utils.Routes

@Composable
fun MainNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.ADD_ITEM + "/{listId}") {
            AddItemScreen()
        }
        composable(Routes.NEW_NOTE + "/{noteId}") {
            NewNoteScreen(){
                navController.popBackStack()
            }
        }
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navController)
        }

    }
}