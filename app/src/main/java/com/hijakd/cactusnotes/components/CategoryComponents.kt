package com.hijakd.cactusnotes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hijakd.cactusnotes.data.CategoryDefaults

@Preview(showBackground = true)
@Composable
fun AddCategory(modifier: Modifier = Modifier, category: String = "") {
    var category by remember { mutableStateOf("") }
    var catsList by remember { mutableStateOf("") }

    for (catItem in CategoryDefaults().loadCategories()){
        catsList = catItem.category
    }

    Column {
        NoteInputText(
            modifier,
            text = category,
            label = "Add Category",
            singleLine = true,
            onTextChange = { category = it })
        NoteButton(modifier, text = "Save") {
            if (category.isNotEmpty() || !catsList.contains(category)){

            }
        }
    }
}

