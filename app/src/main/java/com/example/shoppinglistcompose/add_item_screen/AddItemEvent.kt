package com.example.shoppinglistcompose.add_item_screen

import com.example.shoppinglistcompose.data.AddItem
import com.example.shoppinglistcompose.shopping_list_screen.ShoppingListEvent

sealed class AddItemEvent {
    data class OnDelete(val item: AddItem): AddItemEvent()
    data class OnShowEditDialog(val item: AddItem): AddItemEvent()
    data class OnTextChange(val text: String): AddItemEvent()
    data class OnCheckedChange(val item: AddItem): AddItemEvent()
    object OnItemSave: AddItemEvent()
}