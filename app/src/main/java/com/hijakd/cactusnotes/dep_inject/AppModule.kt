package com.hijakd.cactusnotes.dep_inject

import android.content.Context
import androidx.room.Room
import com.hijakd.cactusnotes.data.CategoryDAO
import com.hijakd.cactusnotes.data.CategoryDb
import com.hijakd.cactusnotes.data.NoteDAO
import com.hijakd.cactusnotes.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/** This is used for bindings in/to Hilt */

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDAO = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideCategoryDao(categoryDb: CategoryDb): CategoryDAO = categoryDb.categoryDAO()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext ctx: Context): NoteDatabase =
        Room.databaseBuilder(ctx, NoteDatabase::class.java, "notes_db").fallbackToDestructiveMigration(false).build()

    @Singleton
    @Provides
    fun provideCategoryDatabase(@ApplicationContext ctx: Context): CategoryDb =
        Room.databaseBuilder(ctx, CategoryDb::class.java, "category_db").fallbackToDestructiveMigration(false).build()
}