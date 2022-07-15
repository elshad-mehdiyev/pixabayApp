package com.myproject.pixabayapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myproject.pixabayapp.constant.Resource
import com.myproject.pixabayapp.data.ImageData
import com.myproject.pixabayapp.data.ImageResponse
import com.myproject.pixabayapp.data.categories.CategoryList

class FakeImageRepository: ImageRepositoryInterface {

    private val imagesData = mutableListOf<ImageData>()

    private val observableImagesData = MutableLiveData<List<ImageData>>(imagesData)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    fun  refreshLiveData() {
        observableImagesData.postValue(imagesData)
    }


    override suspend fun saveImage(imageData: ImageData) {
        imagesData.add(imageData)
        refreshLiveData()
    }

    override suspend fun deleteImage(imageData: ImageData) {
        imagesData.remove(imageData)
        refreshLiveData()
    }

    override fun showAllSavedImage(): LiveData<List<ImageData>> {
        return observableImagesData
    }

    override suspend fun searchImage(searchQuery: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(0,0, listOf()))
        }
    }

    override suspend fun searchImageByColors(
        searchQuery: String,
        colors: String
    ): Resource<ImageResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(0,0, listOf()))
        }
    }

    override suspend fun searchImageByOrder(
        searchQuery: String,
        order: String
    ): Resource<ImageResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(0,0, listOf()))
        }
    }

    override fun showCategoryList(): List<CategoryList> {
        TODO("Not yet implemented")
    }
}