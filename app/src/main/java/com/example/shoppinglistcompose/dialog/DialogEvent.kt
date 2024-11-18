package com.example.shoppinglistcompose.dialog

import com.example.shoppinglistcompose.data.ShoppingListItem
import com.example.shoppinglistcompose.shopping_list_screen.ShoppingListEvent

sealed class DialogEvent {
    data class OnTextChange(val text: String): DialogEvent()
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
}