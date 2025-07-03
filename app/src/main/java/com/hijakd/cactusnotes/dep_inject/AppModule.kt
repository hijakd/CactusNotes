package com.hijakd.cactusnotes.dep_inject

import android.content.Context
import androidx.room.Room
import com.hijakd.cactusnotes.database.CategoriesDb
import com.hijakd.cactusnotes.database.CategoryDAO
import com.hijakd.cactusnotes.database.NoteDAO
import com.hijakd.cactusnotes.database.NotesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(notesDb: NotesDb): NoteDAO = notesDb.noteDAO()

    @Singleton
    @Provides
    fun provideCategoriesDao(categoriesDb: CategoriesDb): CategoryDAO = categoriesDb.categoryDAO()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext ctx: Context): NotesDb =
        Room.databaseBuilder(ctx, NotesDb::class.java, "notes_db").fallbackToDestructiveMigration(false).build()

    @Singleton
    @Provides
    fun provideCategoriesDatabase(@ApplicationContext ctx: Context): CategoriesDb =
        Room.databaseBuilder(ctx, CategoriesDb::class.java, "categories_db").fallbackToDestructiveMigration(false).build()
}