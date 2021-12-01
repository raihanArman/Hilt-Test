package com.raihanarman.testroom.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.shoppinglisttestingyt.other.Event
import com.androiddevs.shoppinglisttestingyt.other.Resource
import com.raihanarman.testroom.data.local.Note
import com.raihanarman.testroom.repositories.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel @ViewModelInject constructor(
    private val repository: NoteRepository
): ViewModel() {
    val noteList = repository.observeAllNotes()

    private val _insertNoteStatus = MutableLiveData<Event<Resource<Note>>>()
    val insertNoteStatus: LiveData<Event<Resource<Note>>> = _insertNoteStatus

    private fun insertNoteIntoDb(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun insertNote(nama: String, umurString: String){
        if(nama.isEmpty() || umurString.isEmpty()){
            _insertNoteStatus.postValue(Event(Resource.error("Field tidak boleh kosong", null)))
        }

        val umur = try {
            umurString.toInt()
        } catch(e: Exception) {
            _insertNoteStatus.postValue(Event(Resource.error("Please enter a valid umur", null)))
            return
        }

        val note = Note(nama, umur)
        insertNoteIntoDb(note)
        _insertNoteStatus.postValue(Event(Resource.success(note)))

    }

}