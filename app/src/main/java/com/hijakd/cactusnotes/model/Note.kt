package com.hijakd.cactusnotes.model

import java.time.Instant
import java.util.Date
import java.util.UUID

class Note(val id: UUID = UUID.randomUUID(),
           val title: String,
           val body: String,
           val category: String,
           val timeStamp: Date = Date.from(Instant.now())
          )