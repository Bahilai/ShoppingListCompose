package com.example.shoppinglistcompose.data

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteRepository {
    suspend fun insertItem(item: NoteItem)
    suspend fun deleteItem(item: NoteItem)
    fun getAllItems(): Flow<List<NoteItem>>
    suspend fun getNoteItemById(id: Int): NoteItem
}