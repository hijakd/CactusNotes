package com.hijakd.cactusnotes.navigation

import com.hijakd.cactusnotes.screens.CategoriesScreen

/* Navigation graph */
enum class ScreenRoutes {
    NotesScreen,
    NewNoteScreen,
    EditNoteScreen,
    CategoriesScreen;

    companion object{
        fun fromRoute(route: String?): ScreenRoutes = when(route?.substringBefore("/")) {
            NotesScreen.name -> NotesScreen
            NewNoteScreen.name -> NewNoteScreen
            EditNoteScreen.name -> EditNoteScreen
            CategoriesScreen.name -> CategoriesScreen
            null -> NotesScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}