package com.myproject.pixabayapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.myproject.pixabayapp.data.ImageData

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImage(imageData: ImageData)
    @Delete
    suspend fun deleteImage(imageData: ImageData)
    @Query("SELECT * FROM ImageData")
    fun showAllSavedImages() : LiveData<List<ImageData>>
    @Query("SELECT * FROM ImageData WHERE id=:id")
    fun selectById(id: Int): ImageData
}