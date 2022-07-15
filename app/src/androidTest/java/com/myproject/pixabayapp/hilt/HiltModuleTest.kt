package com.myproject.pixabayapp.hilt

import android.content.Context
import androidx.room.Room
import com.myproject.pixabayapp.room.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object HiltModuleTest {

    @Provides
    @Named("test_db")
    fun provideTestDb(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context,
        ImageDatabase::class.java
    ).allowMainThreadQueries().build()
}