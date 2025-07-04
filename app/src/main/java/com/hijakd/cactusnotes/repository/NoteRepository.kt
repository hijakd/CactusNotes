package com.hijakd.cactusnotes.repository

import com.hijakd.cactusnotes.database.NoteDAO
import com.hijakd.cactusnotes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDAO: NoteDAO) {
    suspend fun addNote(note: Note) = noteDAO.insert(note)
    suspend fun getNoteById(noteId: String?) = noteDAO.getNoteById(noteId)
    suspend fun updateNote(note: Note) = noteDAO.update(note)
    suspend fun deleteNote(note: Note) = noteDAO.deleteNote(note)
    suspend fun deleteAllNotes() = noteDAO.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDAO.getAllNotes().flowOn(Dispatchers.IO)
}