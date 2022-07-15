package com.myproject.pixabayapp.repo

import androidx.lifecycle.LiveData
import com.myproject.pixabayapp.constant.Resource
import com.myproject.pixabayapp.data.ImageData
import com.myproject.pixabayapp.data.ImageResponse
import com.myproject.pixabayapp.data.categories.CategoryList

interface ImageRepositoryInterface {
    suspend fun saveImage(imageData: ImageData)
    suspend fun deleteImage(imageData: ImageData)
    fun showAllSavedImage(): LiveData<List<ImageData>>
    fun showImageById(id: Int): ImageData
    suspend fun searchImage(searchQuery: String): Resource<ImageResponse>
    suspend fun searchImageByColors(searchQuery: String, colors: String): Resource<ImageResponse>
    suspend fun searchImageByOrder(searchQuery: String, order: String): Resource<ImageResponse>
    fun showCategoryList(): List<CategoryList>
}