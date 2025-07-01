package com.hijakd.cactusnotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.screens.CategoriesScreen
import com.hijakd.cactusnotes.screens.NotesScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.NotesScreen.name){
        composable(ScreenRoutes.NotesScreen.name) {
            NotesScreen(modifier = Modifier, navController)
        }
        composable(ScreenRoutes.CategoriesScreen.name) {
            CategoriesScreen(modifier = Modifier, navController)
        }
    }
}