package com.hijakd.cactusnotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "category_tbl")
class Category(@PrimaryKey val id: UUID = UUID.randomUUID(), @ColumnInfo("category_name") val name: String) {
}