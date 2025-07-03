package com.hijakd.cactusnotes.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hijakd.cactusnotes.model.Category
import com.hijakd.cactusnotes.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepo: CategoryRepository): ViewModel() {
    private val _catList = MutableStateFlow<List<Category>>(emptyList())
    val categoryList = _catList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepo.getAllCategories().distinctUntilChanged().collect { listOfCategories ->
                if (listOfCategories.isEmpty()){
                    Log.d("empty", "Empty category list found")
                } else {
                    _catList.value = listOfCategories
                }
            }
        }
    } // END init

//    fun addCategory(category: Category) = viewModelScope.launch { categoryRepo.addCategory(category) }
//    fun updateCategory(category: Category) = viewModelScope.launch { categoryRepo.updateCategory(category) }
//    fun removeCategory(category: Category) = viewModelScope.launch { categoryRepo.deleteCategory(category) }
}