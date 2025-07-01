package com.hijakd.cactusnotes.database


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hijakd.cactusnotes.model.Categories
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Categories)

    @Query("SELECT * FROM category_tbl")
    fun getCategories(): Flow<List<Categories>>

    @Query("SELECT * FROM category_tbl WHERE id = :id")
    suspend fun getCategoryById(id: String): Categories // this may need to be Int not String

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(category: Categories)

    @Delete
    suspend fun deleteCategory(category: Categories)
}