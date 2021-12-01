package com.raihanarman.testroom.repositories

import androidx.lifecycle.LiveData
import com.raihanarman.testroom.data.local.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun observeAllNotes(): LiveData<List<Note>>

}