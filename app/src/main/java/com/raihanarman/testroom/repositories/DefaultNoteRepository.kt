package com.raihanarman.testroom.repositories

import androidx.lifecycle.LiveData
import com.raihanarman.testroom.data.local.Note
import com.raihanarman.testroom.data.local.NoteDao
import javax.inject.Inject

class DefaultNoteRepository @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override fun observeAllNotes(): LiveData<List<Note>> {
        return noteDao.observeAllNotes()
    }
}