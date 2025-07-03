package com.hijakd.cactusnotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.database.CategoryDefaults
import com.hijakd.cactusnotes.model.Category
import com.hijakd.cactusnotes.screens.CategoriesScreen
import com.hijakd.cactusnotes.screens.NotesScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val categoryList = emptyList<Category>()
//    val categoryList = CategoryDefaults().loadCategories()

//    NavHost(navController = navController, startDestination = ScreenRoutes.NotesScreen.name){
    NavHost(navController = navController, startDestination = ScreenRoutes.CategoriesScreen.name){
        composable(ScreenRoutes.NotesScreen.name) {
            NotesScreen(modifier = Modifier, navController)
        }
        // TODO replace categories parameter with database entries
        composable(ScreenRoutes.CategoriesScreen.name) {
            CategoriesScreen(modifier = Modifier, categories = categoryList, navController = navController)
        }
    }
}