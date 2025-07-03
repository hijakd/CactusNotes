package com.hijakd.cactusnotes.components

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.hijakd.cactusnotes.model.Category

@Composable
fun CategoriesDropDown(modifier: Modifier = Modifier, expandCategories: MutableState<Boolean>, categoriesList: List<Category>, menuItemSelected: MutableState<Boolean>, noteCategory: MutableState<String>) {

//    var dropDownMenuItemSelected by remember { mutableStateOf(false) }
//    var noteCategory by remember { mutableStateOf("") }

    DropdownMenu(expanded = expandCategories.value, onDismissRequest = { expandCategories.value = false }) {
        for (category in categoriesList) {
            DropdownMenuItem(
                text = {
                    if (menuItemSelected.value && category.name == noteCategory.value) {
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Black)
                        )
                    } else {
                        Text(text = category.name, style = MaterialTheme.typography.bodyMedium)
                    }
                }, modifier = modifier.background(
                    if (menuItemSelected.value && category.name == noteCategory.value) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                    } else {
                        Color.Unspecified
                    }
                ), onClick = {
                    /* TODO: pass category item back */
                    noteCategory.value = category.name
                    menuItemSelected.value = true
                    expandCategories.value = false
                })
        }

    }
}