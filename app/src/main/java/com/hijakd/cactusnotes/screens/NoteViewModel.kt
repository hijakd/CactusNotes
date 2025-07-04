package com.hijakd.cactusnotes.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * this handles the "State" of the application, the data state has
 * been "hoisted" out of the application logic, hence decoupling it
 */

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val _notesList = MutableStateFlow<List<Note>>(emptyList())
    val notesList = _notesList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                if (listOfNotes.isEmpty()) {
                    Log.d("empty", "Empty list found")
                } else {
                    _notesList.value = listOfNotes
                }
            }
        }
    } // END of init

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }


}