package com.hijakd.cactusnotes.components

import android.R.attr.onClick
import android.R.attr.text
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.hijakd.cactusnotes.data.CategoryDefaults
import com.hijakd.cactusnotes.model.Categories
import com.hijakd.cactusnotes.ui.theme.Black
import com.hijakd.cactusnotes.ui.theme.Pink80

@Preview(showBackground = true)
@Composable
fun AddCategory(modifier: Modifier = Modifier, category: String = "") {
    var category by remember { mutableStateOf("") }
    Column {
        NoteInputText(
            modifier,
            text = category,
            label = "Add Category",
            singleLine = true,
            onTextChange = { category = it })
        NoteButton(modifier, text = "Save") {
            /*if (category.isNotEmpty()*//* || category != "cactus?!?"*//*){

            }*/
        }
    }
}

@Composable
fun MinimalDropdownMenu(modifier: Modifier = Modifier, categoryList: List<Categories>) {
    var expandDropDown by remember { mutableStateOf(false) }
//    var dropMenuString by remember { mutableStateOf("") }
    var catsList: MutableList<Categories>

    if (categoryList.isEmpty()){
        catsList = CategoryDefaults().loadCategories() as MutableList<Categories>
    } else {
        catsList = categoryList as MutableList<Categories>
    }

//    dropMenuString = catsList[0].category
//    Toast.makeText(ctx, "", Toast.LENGTH_SHORT).show()

    Column(
        modifier = Modifier
                .padding(16.dp)
                .background(Pink80), horizontalAlignment = Alignment.Start
    ) {
        IconButton(onClick = { expandDropDown = !expandDropDown }) {
            Icon(
                Icons.Rounded.ArrowDropDown,
                contentDescription = "More options",
                modifier.size(32.dp)
            )
        }
        DropdownMenu(
            expanded = expandDropDown,
            onDismissRequest = { expandDropDown = false },
            offset = DpOffset(
                x = -70.dp,
                y = 0.dp
            )
        ) {
//            DropdownMenuItem(
////                text = { Text("Option 1") },
////                text = { Text(text = dropMenuString, style = TextStyle(color = Black)) },
//                text = { Text(text = catsList[0].category.toString(), style = TextStyle(color = Black)) },
//                onClick = { /* Do something... */ }
//            )

            for (category in catsList){
                DropdownMenuItem(
                    text = {Text(text = category.category, style = TextStyle(color = Black))},
                    onClick = {}
                )
            }
        }
    }
}
