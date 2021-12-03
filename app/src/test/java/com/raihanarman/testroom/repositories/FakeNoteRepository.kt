package com.raihanarman.testroom.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raihanarman.testroom.data.local.Note

class FakeNoteRepository: NoteRepository {

    private val noteList = mutableListOf<Note>()
    private val observableNotes = MutableLiveData<List<Note>>(noteList)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableNotes.postValue(noteList)
    }

    override suspend fun insertNote(note: Note) {
        noteList.add(note)
        refreshLiveData()
    }

    override suspend fun deleteNote(note: Note) {
        noteList.remove(note)
        refreshLiveData()
    }

    override fun observeAllNotes(): LiveData<List<Note>> {
        return observableNotes
    }
}