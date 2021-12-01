package com.raihanarman.testroom.di

import android.content.Context
import androidx.room.Room
import com.raihanarman.testroom.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named


@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {
    @Provides
    @Named("note_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}