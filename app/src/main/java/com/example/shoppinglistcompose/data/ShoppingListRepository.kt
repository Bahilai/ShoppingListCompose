package com.example.shoppinglistcompose.data

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListRepository {

    suspend fun insertItem(item: ShoppingListItem)
    suspend fun deleteItem(item: ShoppingListItem)
    fun getAllItems(): Flow<List<ShoppingListItem>>
}