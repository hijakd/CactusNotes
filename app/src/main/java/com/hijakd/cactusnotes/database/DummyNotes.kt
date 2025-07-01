package com.hijakd.cactusnotes.database

import com.hijakd.cactusnotes.model.Note

class DummyNotes {
    fun loadNotes(): List<Note> {
        return listOf(Note(title = "A good day", body = "We went on a vacation by the lake", category = "Dummy"),
                      Note(title = "Title, but no body", body = "", category = "Dummy"),
                      Note(title = "What is Lorem Ipsum?", body = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", category = "Lorem"),
                      Note(title = "Why do we use it?", body = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", category = "Lorem"),
                      Note(title = "Where does it come from?", body = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.", category = "Lorem"),
                      Note(title = "Android Compose", body = "Working on Android Compose course today", category = "Android"),
                      Note(title = "Keep at it...", body = "Sometimes things just happen", category = "Android"),
                      Note(title = "A TV day", body = "Watching TV alone today", category = "Dummy"),
                      Note(title = "A movie day", body = "Watching a movie with family today", category = "Dummy"),
                      Note(title = "Another movie day", body = "Watching a movie with friends today", category = "Dummy"),
                      Note(title = "A day to walk", body = "The beach can be a good place to go walking", category = "Dummy"),
        )
    }
}