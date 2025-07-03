package com.hijakd.cactusnotes.navigation

/* Navigation graph */
enum class ScreenRoutes {
    NotesScreen,
    NewNoteScreen,
    CategoriesScreen;

    companion object{
        fun fromRoute(route: String?): ScreenRoutes = when(route?.substringBefore("/")) {
            NotesScreen.name -> NotesScreen
            NewNoteScreen.name -> NewNoteScreen
            CategoriesScreen.name -> CategoriesScreen
            null -> NotesScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}