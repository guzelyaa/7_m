package com.example.m7l1.di

import android.content.Context
import androidx.room.Room
import com.example.m7l1.data.local.AppDatabase
import com.example.m7l1.data.local.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun appDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "Note-db"
    ).build()

    @Provides
    fun noteDao(appDatabase: AppDatabase): NoteDao = appDatabase.noteDao()
}