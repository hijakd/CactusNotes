package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.components.CategoryCard
import com.hijakd.cactusnotes.components.DefaultCategoriesDialog
import com.hijakd.cactusnotes.components.NavDropDownMenu
import com.hijakd.cactusnotes.components.SaveButton
import com.hijakd.cactusnotes.components.TextInput
import com.hijakd.cactusnotes.database.CategoryDefaults
import com.hijakd.cactusnotes.model.Category
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme
import com.hijakd.cactusnotes.ui.theme.LightGrey
import com.hijakd.cactusnotes.ui.theme.PurpleGrey
import com.hijakd.cactusnotes.utils.getHalfWidth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(modifier: Modifier = Modifier,
                     categories: List<Category>,
                     menuStatus: MutableState<Boolean>,
                     navController: NavHostController,
                     onRemoveCategory: (Category) -> Unit,
                     onAddCategory: (Category) -> Unit) {

    val dropMenuItemSelected = remember { mutableStateOf(false) }
    val canSaveCategory = remember { mutableStateOf(false) }
    val category = remember { mutableStateOf("") }

    var canAddNewCategory by remember { mutableStateOf(false) }

    val catsList: MutableList<Category> = if (categories.isEmpty()) {
        CategoryDefaults().loadCategories() as MutableList<Category>
    } else {
        categories as MutableList<Category>
    }

    if (categories.isEmpty()) {
        DefaultCategoriesDialog(modifier)
    }

    Scaffold(modifier.fillMaxSize(), topBar = {
        /*TopBar(
            modifier,
            title = "Categories",
            menuStatus = menuStatus,
            navController = navController,
            saveIcon = true,
            editNote = false,
            dropMenuItemSelected,
            category,
            saveCategory = {onAddCategory(Category(name = category.value))}
        )*/
        TopAppBar(
            title = { Text("Categories") },
            navigationIcon = {
                IconButton(onClick = { menuStatus.value = true }) {
                    Icon(
                        Icons.Rounded.Menu,
                        modifier = modifier
                                .padding(horizontal = 10.dp),
                        contentDescription = "menu icon",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    NavDropDownMenu(menuStatus, navController)
                }
            },
            actions = {
                /* Save button */
                IconButton(onClick = { onAddCategory(Category(name = category.value)) }) {
                    Icon(
                        Icons.Rounded.Save,
                        contentDescription = "save item icon",
                        modifier
                                .padding(end = 15.dp)
                                .size(37.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

    }) {

        /* "header" Column */
        Column(
            modifier
                    .padding(it)
                    .fillMaxWidth()
                    .background(LightGrey),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(7.dp))

            TextInput(
                modifier = modifier
                        .padding(top = 6.dp, bottom = 7.dp)
                        .fillMaxWidth(0.9f),
                text = category.value,
                label = "Category"
            ) { catItem ->
                for (cat in catsList) {
                    if (catItem == cat.name) {
                        null
                    } else {
                        category.value = catItem
                    }
                }
            }

            SaveButton { Category(name = category.value) }

            HorizontalDivider(
                modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                thickness = 1.dp,
                color = PurpleGrey
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = getHalfWidth())
            ) {
                items(catsList.count()) { categoryItem ->
                    CategoryCard(category = catsList[categoryItem])
                }
            }
        } // END "header" Column
    } // END of Scaffold
}

@Preview(showBackground = true)
@Composable
fun ShowCategoriesScreen() {
    val navController = rememberNavController()
    val menuStatus = remember { mutableStateOf(false) }

    CactusNotesTheme {
        CategoriesScreen(
            categories = CategoryDefaults().loadCategories(),
            menuStatus = menuStatus,
            navController = navController,
            onRemoveCategory = {},
            onAddCategory = {})
    }
}