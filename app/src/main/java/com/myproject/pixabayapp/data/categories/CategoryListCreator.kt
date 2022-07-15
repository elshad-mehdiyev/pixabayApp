package com.myproject.pixabayapp.data.categories

import com.myproject.pixabayapp.R

class CategoryListCreator {
    fun listOfCategory() : List<CategoryList> {
        return listOf(
            CategoryList(R.string.flower,R.drawable.add_source_flower),
            CategoryList(R.string.car,R.drawable.add_source_car),
            CategoryList(R.string.cute,R.drawable.add_source_cute),
            CategoryList(R.string.animal,R.drawable.add_source_animal),
            CategoryList(R.string.city, R.drawable.add_source_city),
            CategoryList(R.string.galaxy, R.drawable.add_source_galaxy),
            CategoryList(R.string.history, R.drawable.add_source_history),
            CategoryList(R.string.nature, R.drawable.add_source_nature),
            CategoryList(R.string.techno, R.drawable.add_source_techology),
            CategoryList(R.string.game, R.drawable.add_source_game)
        )
    }
}