package com.hijakd.cactusnotes.data

import com.hijakd.cactusnotes.model.Note

class DummyNotes {
    fun loadNotes(): List<Note> {
        return listOf(Note(title = "A good day", body = "We went on a vacation by the lake"),
                      Note(title = "Android Compose", body = "Working on Android Compose course today"),
                      Note(title = "Keep at it...", body = "Sometimes things just happen"),
                      Note(title = "A TV day", body = "Watching TV alone today"),
                      Note(title = "A movie day", body = "Watching a movie with family today"),
                      Note(title = "Another movie day", body = "Watching a movie with friends today"),
                      Note(title = "A day to walk", body = "The beach can be a good place to go walking"),
                      Note(title = "What is Lorem Ipsum?", body = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                      Note(title = "Why do we use it?", body = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."),
                      Note(title = "Where does it come from?", body = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old."))
    }
}