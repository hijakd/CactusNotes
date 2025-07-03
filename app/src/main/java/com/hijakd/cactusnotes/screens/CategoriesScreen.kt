package com.hijakd.cactusnotes.screens

//import com.hijakd.cactusnotes.components.CategoriesDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
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
import com.hijakd.cactusnotes.components.DefaultCategoriesDialog
import com.hijakd.cactusnotes.components.CategoryCard
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
fun CategoriesScreen(modifier: Modifier = Modifier, categories: List<Category>, navController: NavHostController) {

    var canAddNewCategory by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf("") }

    val catsList: MutableList<Category> = if (categories.isEmpty()) {
        CategoryDefaults().loadCategories() as MutableList<Category>
    } else {
        categories as MutableList<Category>
    }

    if (categories.isEmpty()) {
        DefaultCategoriesDialog(modifier)
    }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text("Categories") },
            /*navigationIcon = {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    modifier = modifier.padding(5.dp).clickable{TODO()} ,
                    contentDescription = "back arrow",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },*/
            actions = {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "add new category",
                    modifier
                            .padding(end = 15.dp)
                            .size(37.dp)
                            .clickable { canAddNewCategory = !canAddNewCategory },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) // END of TopAppBar
    }) {


        // "header" Column
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
                text = category,
                label = "Category"
            ) { catItem ->
                category = catItem
            }

            SaveButton { Category(name = category) }

            HorizontalDivider(
                modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                thickness = 1.dp,
                color = PurpleGrey
            )

//            val halfWidth = getHalfWidth()
            LazyVerticalGrid(
//                columns = GridCells.Adaptive(minSize = halfWidth)
                columns = GridCells.Adaptive(minSize = getHalfWidth())
            ) {
                items(catsList.count()) { categoryItem ->
//                    Card(modifier
//                                 .fillMaxWidth(0.9f)
//                                 .padding(horizontal = 5.dp, vertical = 3.dp)) {
//                        Surface(modifier = modifier.padding(horizontal = 7.dp, vertical = 4.dp)) {
//                            Row {
//                                Spacer(modifier = Modifier.size(5.dp))
//                                Text(text = catsList[categoryItem].name)
//                            }
//                        }
//                    }
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

    CactusNotesTheme {
        CategoriesScreen(categories = CategoryDefaults().loadCategories(), navController = navController)
    }

}