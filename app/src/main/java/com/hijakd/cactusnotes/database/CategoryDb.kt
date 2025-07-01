package com.hijakd.cactusnotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hijakd.cactusnotes.model.Categories

/** TODO: consider changing the exportSchema to true */
@Database(entities = [Categories::class], version = 1, exportSchema = false)
abstract class CategoryDb: RoomDatabase(){
    abstract fun categoryDAO(): CategoryDAO
}