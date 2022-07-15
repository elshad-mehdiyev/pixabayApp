package com.myproject.pixabayapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.pixabayapp.constant.Event
import com.myproject.pixabayapp.constant.Resource
import com.myproject.pixabayapp.data.ImageData
import com.myproject.pixabayapp.data.ImageResponse
import com.myproject.pixabayapp.repo.ImageRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PixabayAppViewModel @Inject constructor(
    private val repository: ImageRepositoryInterface
) : ViewModel() {

    private val _imageFromApi = MutableLiveData<Event<Resource<ImageResponse>>>()
    val imageFromApi: LiveData<Event<Resource<ImageResponse>>>  = _imageFromApi

    private val _imageFromDB = getSavedImageData()
    val imageFromDB: LiveData<List<ImageData>> = _imageFromDB

    var categoryList = repository.showCategoryList()


    fun insertDataIntoDB(imageData: ImageData) = viewModelScope.launch {
        repository.saveImage(imageData)
    }
    fun deleteData(imageData: ImageData) = viewModelScope.launch {
        repository.deleteImage(imageData)
    }
    fun getImageById(id: Int) = repository.showImageById(id)

    fun getSavedImageData() = repository.showAllSavedImage()


    fun searchImage(searchQuery: String) {
        if (searchQuery.isEmpty()) {
            return
        }
        _imageFromApi.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchImage(searchQuery)
            _imageFromApi.value = Event(response)
        }
    }
    fun searchImageByColors(searchQuery: String, colors: String) {
        if (searchQuery.isEmpty() || colors.isEmpty()) {
            return
        }
        _imageFromApi.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchImageByColors(searchQuery, colors)
            _imageFromApi.value = Event(response)
        }
    }
    fun searchImageByOrder(searchQuery: String, order: String) {
        if (searchQuery.isEmpty() || order.isEmpty()) {
            return
        }
        _imageFromApi.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchImageByOrder(searchQuery, order)
            _imageFromApi.value = Event(response)
        }
    }
}