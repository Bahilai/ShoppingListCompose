package com.example.shoppinglistcompose.settings_screen

import androidx.compose.ui.graphics.Color
import com.example.shoppinglistcompose.ui.theme.GreenLight
import com.example.shoppinglistcompose.ui.theme.Red
import com.example.shoppinglistcompose.ui.theme.Yellow

object ColorUtils {
    val colorList = listOf(
        "#0079B4",
        "#CC00FF",
        "#2EFF00",
        "#f28f93",
        "#ff00a1",
        "#041cf6",
        "#532a4a",
        "#774084",
        "#09cf6a",
        "#668096",
        "#190804"
    )

    fun getProgressColor(progress: Float): Color{
        return when(progress){
            in 0f..0.339f -> Red
            in 0.34f..0.669f -> Yellow
            in 0.67f..1.0f -> GreenLight
            else -> Red
        }
    }
}