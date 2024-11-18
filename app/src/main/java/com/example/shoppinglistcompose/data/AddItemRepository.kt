package com.example.shoppinglistcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AddItemRepository {
    suspend fun insertItem(item: AddItem)
    suspend fun deleteItem(item: AddItem)
    fun getAllItemsById(listId: Int): Flow<List<AddItem>>
    suspend fun getListItemById(listId: Int): ShoppingListItem
    suspend fun insertItem(item: ShoppingListItem)
}