package com.myproject.pixabayapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myproject.pixabayapp.data.ImageData

@Database(entities = [ImageData::class], version = 1, exportSchema = false)
abstract class ImageDatabase: RoomDatabase() {
    abstract fun getDao(): ImageDao
}