package com.hijakd.cactusnotes.repository

import com.hijakd.cactusnotes.database.CategoryDAO
import com.hijakd.cactusnotes.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDAO: CategoryDAO) {
    suspend fun addCategory(category: Category) = categoryDAO.insert(category)
    suspend fun updateCategory(category: Category) = categoryDAO.update(category)
    suspend fun deleteCategory(category: Category) = categoryDAO.deleteCategory(category)
    fun getAllCategories(): Flow<List<Category>> = categoryDAO.getAllCategories().flowOn(Dispatchers.IO)
}