package com.hijakd.cactusnotes.screens


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hijakd.cactusnotes.database.CategoryDefaults
import com.hijakd.cactusnotes.model.Categories
import com.hijakd.cactusnotes.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepo: CategoryRepository) : ViewModel() {
    private val _catsList = MutableStateFlow<List<Categories>>(emptyList())
    val catsList = _catsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepo.getAllCategories().distinctUntilChanged().collect { listOfCategories ->
                if (listOfCategories.isEmpty()) {
                    Log.d("emptyCats", "Empty category list found")
//                    _catsList.value = CategoryDefaults().loadCategories()
                } else {
                    _catsList.value = listOfCategories
                }
            }
        }
    } // END of init

    fun addCategory(category: Categories) = viewModelScope.launch { categoryRepo.addCategory(category) }
    fun removeCategory(category: Categories) = viewModelScope.launch { categoryRepo.deleteCategory(category) }
}