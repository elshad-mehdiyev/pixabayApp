package com.myproject.pixabayapp.api

import com.myproject.pixabayapp.constant.Constants.API_KEY
import com.myproject.pixabayapp.data.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("/api/")
    suspend fun searchImage(
        @Query("q") searchQuery: String,
        @Query("key") key: String = API_KEY
    ): Response<ImageResponse>
    @GET("/api/")
    suspend fun searchImageByColors(
        @Query("q") searchQuery: String,
        @Query("colors") colors: String,
        @Query("key") key: String = API_KEY
    ): Response<ImageResponse>
    @GET("/api/")
    suspend fun searchImageByOrder(
        @Query("q") searchQuery: String,
        @Query("order") order: String,
        @Query("key") key: String = API_KEY
    ): Response<ImageResponse>
}