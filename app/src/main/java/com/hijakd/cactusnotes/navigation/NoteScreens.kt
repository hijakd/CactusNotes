package com.hijakd.cactusnotes.navigation

enum class ScreenRoutes {
    NotesScreen,
    EditCategories;

    companion object{
        fun fromRoute(route: String?): ScreenRoutes = when (route?.substringBefore("/")){
            NotesScreen.name -> NotesScreen
            EditCategories.name -> EditCategories
            null -> NotesScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}