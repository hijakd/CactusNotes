package com.hijakd.cactusnotes.navigation

enum class ScreenRoutes {
    NotesScreen,
    CategoriesScreen;

    companion object{
        fun fromRoute(route: String?): ScreenRoutes = when(route?.substringBefore("/")) {
            NotesScreen.name -> NotesScreen
            CategoriesScreen.name -> CategoriesScreen
            null -> NotesScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}