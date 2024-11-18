package com.example.shoppinglistcompose.main_screen

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.shoppinglistcompose.ui.theme.BlueLight


@Composable
fun BottomNav(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val listItems = listOf(
        BottomNavItem.ListItem,
        BottomNavItem.NoteItem,
        BottomNavItem.AboutItem,
        BottomNavItem.SettingsItem
    )
    //BottomAppBar(back)
    NavigationBar {
        val selectedItemPosition = remember {
            mutableStateOf(0)
        }
        listItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = selectedItemPosition.value == index,
                onClick = {
                    onNavigate(bottomNavItem.route)
                    selectedItemPosition.value = index
                          },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomNavItem.iconId),
                        contentDescription = null,
                        tint = Color.Unspecified
                        )
                },
                label = {
                    Text(text = bottomNavItem.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Gray,
                    selectedTextColor = Color.Gray,
                    unselectedTextColor = Color.White,
                    indicatorColor = BlueLight
                )
            )
        }
    }
}