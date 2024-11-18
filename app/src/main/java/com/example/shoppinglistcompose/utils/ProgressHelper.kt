package com.example.shoppinglistcompose.utils

object ProgressHelper {
    fun getProgress(allItemsCount: Int, allSelectedItemsCount: Int): Float {
        if (allItemsCount == 0) return 0.0f
        return allSelectedItemsCount.toFloat() / allItemsCount.toFloat()
    }
}