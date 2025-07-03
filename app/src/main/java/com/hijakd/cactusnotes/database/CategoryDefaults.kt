package com.hijakd.cactusnotes.database

import com.hijakd.cactusnotes.model.Category

class CategoryDefaults {
    fun loadCategories(): List<Category> {
        return listOf(Category(name = "cactus?!?"),
                      Category(name = "Books"),
                      Category(name = "Business"),
                      Category(name = "Craft"),
                      Category(name = "Crafting"),
                      Category(name = "Films/Movies"),
                      Category(name = "Food"),
                      Category(name = "Hobby"),
                      Category(name = "Hobbies"),
                      Category(name = "Music"),
                      Category(name = "Personal"),
                      Category(name = "Shopping"),
                      Category(name = "Things to do"),
                      Category(name = "TODO"),
                      Category(name = "Wishlist"),
                      Category(name = "Work"),
        )
    }
}