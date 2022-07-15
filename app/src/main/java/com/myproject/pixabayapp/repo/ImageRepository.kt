package com.myproject.pixabayapp.repo

import androidx.lifecycle.LiveData
import com.myproject.pixabayapp.api.ImageApi
import com.myproject.pixabayapp.constant.Resource
import com.myproject.pixabayapp.data.ImageData
import com.myproject.pixabayapp.data.ImageResponse
import com.myproject.pixabayapp.data.categories.CategoryList
import com.myproject.pixabayapp.data.categories.CategoryListCreator
import com.myproject.pixabayapp.room.ImageDao
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val dao: ImageDao,
    private val api: ImageApi,
    private val categoryList: CategoryListCreator
): ImageRepositoryInterface {
    override suspend fun saveImage(imageData: ImageData) {
        dao.saveImage(imageData)
    }

    override suspend fun deleteImage(imageData: ImageData) {
        dao.deleteImage(imageData)
    }

    override fun showAllSavedImage(): LiveData<List<ImageData>> {
        return dao.showAllSavedImages()
    }

    override fun showImageById(id: Int): ImageData {
        return dao.selectById(id)
    }

    override suspend fun searchImage(searchQuery: String): Resource<ImageResponse> {
        return try {
            val response = api.searchImage(searchQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun searchImageByColors(searchQuery: String, colors: String): Resource<ImageResponse> {
        return try {
            val response = api.searchImageByColors(searchQuery, colors)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun searchImageByOrder(searchQuery: String, order: String): Resource<ImageResponse> {
        return try {
            val response = api.searchImageByOrder(searchQuery, order)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override fun showCategoryList(): List<CategoryList> {
        return categoryList.listOfCategory()
    }
}