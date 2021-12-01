package com.raihanarman.testroom.di

import android.content.Context
import androidx.room.Room
import com.raihanarman.testroom.data.local.NoteDao
import com.raihanarman.testroom.data.local.NoteDatabase
import com.raihanarman.testroom.repositories.DefaultNoteRepository
import com.raihanarman.testroom.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, NoteDatabase::class.java, "note_db").build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: NoteDatabase
    ) = database.noteDao()

    @Singleton
    @Provides
    fun provideDefaultNoteRepository(
        dao: NoteDao,
    ) = DefaultNoteRepository(dao) as NoteRepository


}