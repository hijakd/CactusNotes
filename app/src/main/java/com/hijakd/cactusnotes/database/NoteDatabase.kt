package com.hijakd.cactusnotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.utils.DateConverter

/** TODO: consider changing the exportSchema to true */
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDAO
}