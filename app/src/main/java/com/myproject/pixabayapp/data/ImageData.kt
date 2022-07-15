package com.myproject.pixabayapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageData(
    @PrimaryKey(autoGenerate = true)
    val uuid: Int? = null,
    val comments: Int? = null,
    val id: Int? = null,
    val likes: Int? = null,
    val tags: String? = null,
    val user: String? = null,
    val userImageURL: String? = null,
    val views: Int? = null,
    val webformatURL: String? = null,
)