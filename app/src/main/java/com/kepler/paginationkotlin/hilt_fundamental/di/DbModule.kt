package com.kepler.paginationkotlin.hilt_fundamental.di

import android.content.Context
import androidx.room.Room
import com.kepler.paginationkotlin.hilt_fundamental.db.NoteDatabase
import com.kepler.paginationkotlin.hilt_fundamental.db.NoteEntity
import com.kepler.paginationkotlin.hilt_fundamental.utils.Constants.NOTE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,NoteDatabase::class.java, NOTE_DATABASE)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideDao(db:NoteDatabase) = db.notDao()

    @Provides
    fun provideEntity() = NoteEntity()

}