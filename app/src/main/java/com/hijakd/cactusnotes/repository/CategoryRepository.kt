package com.hijakd.cactusnotes.repository

import com.hijakd.cactusnotes.data.CategoryDAO
import com.hijakd.cactusnotes.model.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDAO: CategoryDAO){
    suspend fun addCategory(category: Categories) = categoryDAO.insert(category)
    suspend fun updateCategory(category: Categories) = categoryDAO.update(category)
    suspend fun deleteCategory(category: Categories) = categoryDAO.deleteCategory(category)
    fun getAllCategories(): Flow<List<Categories>> = categoryDAO.getCategories().flowOn(Dispatchers.IO)
}
