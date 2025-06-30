package com.hijakd.cactusnotes.data

import com.hijakd.cactusnotes.model.Categories

class CategoryDefaults {
    fun loadCategories(): List<Categories> {
        return listOf(Categories(category = "cactus?!?"),
                      Categories(category = "Books"),
                      Categories(category = "Business"),
                      Categories(category = "Craft"),
                      Categories(category = "Crafting"),
                      Categories(category = "Films/Movies"),
                      Categories(category = "Food"),
                      Categories(category = "Hobby"),
                      Categories(category = "Hobbies"),
                      Categories(category = "Music"),
                      Categories(category = "Personal"),
                      Categories(category = "Shopping"),
                      Categories(category = "Things to do"),
                      Categories(category = "TODO"),
                      Categories(category = "Wishlist"),
                      Categories(category = "Work"),
        )
    }
}