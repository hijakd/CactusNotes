package com.hijakd.cactusnotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hijakd.cactusnotes.model.Category

/** TODO: consider changing the exportSchema to true */
@Database(entities = [Category::class], version = 1, exportSchema = false)
abstract class CategoriesDb: RoomDatabase() {
    abstract fun categoryDAO(): CategoryDAO
}