package com.example.shoppinglistcompose.di

import android.app.Application
import androidx.room.Room
import com.example.shoppinglistcompose.data.AddItemRepoImpl
import com.example.shoppinglistcompose.data.AddItemRepository
import com.example.shoppinglistcompose.data.MainDb
import com.example.shoppinglistcompose.data.NoteRepoImpl
import com.example.shoppinglistcompose.data.NoteRepository
import com.example.shoppinglistcompose.data.ShoppingListRepoImpl
import com.example.shoppinglistcompose.data.ShoppingListRepository
import com.example.shoppinglistcompose.data_store.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "shop_list_db"
        ).createFromAsset("shop_list_db.db").build()
    }

    @Provides
    @Singleton
    fun provideShopRepo(db: MainDb): ShoppingListRepository {
        return ShoppingListRepoImpl(db.shoppingListDao)
    }

    @Provides
    @Singleton
    fun provideAddItemDao(db: MainDb): AddItemRepository {
        return AddItemRepoImpl(db.addItemDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepo(db: MainDb): NoteRepository {
        return NoteRepoImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(app: Application): DataStoreManager {
        return DataStoreManager(app)
    }
}