package com.hijakd.cactusnotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_tbl")
data class Categories(@ColumnInfo("category_name") val category: String){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
