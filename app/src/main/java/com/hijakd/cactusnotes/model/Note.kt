package com.hijakd.cactusnotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl")
class Note(@PrimaryKey val id: UUID = UUID.randomUUID(),
           @ColumnInfo("note_title") val title: String,
           @ColumnInfo("note_body") val body: String,
           @ColumnInfo("note_category") val category: String,
           @ColumnInfo("note_date_created") val timeStamp: Date = Date.from(Instant.now()),
           @ColumnInfo("note_last_edited") val editedTimeStamp: Date = Date.from(Instant.now())
          )