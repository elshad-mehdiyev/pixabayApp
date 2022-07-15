package com.myproject.pixabayapp.data

data class ImageResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<ImageData>
)