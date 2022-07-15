package com.myproject.pixabayapp.hilt

import android.content.Context
import androidx.room.Room
import com.myproject.pixabayapp.api.ImageApi
import com.myproject.pixabayapp.constant.Constants.BASE_URL
import com.myproject.pixabayapp.constant.Constants.DB_NAME
import com.myproject.pixabayapp.data.categories.CategoryListCreator
import com.myproject.pixabayapp.repo.ImageRepository
import com.myproject.pixabayapp.repo.ImageRepositoryInterface
import com.myproject.pixabayapp.room.ImageDao
import com.myproject.pixabayapp.room.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ImageDatabase::class.java,
        DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(
        db: ImageDatabase
    ) = db.getDao()

    @Singleton
    @Provides
    fun provideImageApi(): ImageApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(ImageApi::class.java)
    }
    @Singleton
    @Provides
    fun provideRepositoryInterface(
        dao: ImageDao,
        api: ImageApi,
        categoryListCreator: CategoryListCreator
    ) = ImageRepository(dao, api, categoryListCreator) as ImageRepositoryInterface

    @Singleton
    @Provides
    fun provideCategoryList() = CategoryListCreator()
}