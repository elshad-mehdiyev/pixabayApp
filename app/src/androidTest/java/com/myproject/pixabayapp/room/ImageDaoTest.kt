package com.myproject.pixabayapp.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.myproject.pixabayapp.data.ImageData
import com.myproject.pixabayapp.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ImageDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Inject
    @Named("test_db")
    lateinit var db: ImageDatabase
    private lateinit var dao: ImageDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = db.getDao()
    }
    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insert_data_to_db_return_true() = runTest {
        val imageData = ImageData(
            uuid = 1, "bird", 12, 12, 15,
            15, 15, "user", "url", "url", "url"
        )
        dao.saveImage(imageData)
        val data = dao.showAllSavedImages().getOrAwaitValue()
        assertThat(data).contains(imageData)
    }

    @Test
    fun delete_data_from_db_return_true() = runTest {
        val imageData = ImageData(
            uuid = 1, "bird", 12, 12, 15,
            15, 15, "user", "url", "url", "url"
        )
        dao.saveImage(imageData)
        dao.deleteImage(imageData)
        val data = dao.showAllSavedImages().getOrAwaitValue()
        assertThat(data).doesNotContain(imageData)
    }
}