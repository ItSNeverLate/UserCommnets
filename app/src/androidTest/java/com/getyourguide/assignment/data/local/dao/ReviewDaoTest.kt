package com.getyourguide.assignment.data.local.dao

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult.Page
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.getyourguide.assignment.data.local.AppDatabase
import com.getyourguide.assignment.data.local.entity.AuthorEntity
import com.getyourguide.assignment.data.local.entity.ReviewEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
@SmallTest
class ReviewDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var reviewDao: ReviewDao

    /**
     * Dummy Data
     */
    private val review1 = ReviewEntity(1, 1,
        AuthorEntity("FullName1", null, null), "", "", false, "", "", 1, 5f, "", "")
    private val review2 = ReviewEntity(2, 1,
        AuthorEntity("FullName2", null, null), "", "", false, "", "", 1, 5f, "", "")
    private val reviewList = listOf(review1,review2)
    private val emptyList = listOf<ReviewEntity>()

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        reviewDao = db.reviewDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAll() = runBlocking {
        reviewDao.insertAll(reviewList)

        val pagingSource = reviewDao.getAll()

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val expected = Page(
            data = reviewList,
            prevKey = null,
            nextKey = null,
            itemsAfter = 0,
            itemsBefore = 0,
        )

        assertThat(expected).isEqualTo(result)
    }

    @Test
    fun getAll() = runBlocking {
        reviewDao.insertAll(reviewList)

        val pagingSource = reviewDao.getAll()

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val expected = Page(
            data = reviewList,
            prevKey = null,
            nextKey = null,
            itemsAfter = 0,
            itemsBefore = 0,
        )

        assertThat(expected).isEqualTo(result)
    }

    @Test
    fun deleteAll() = runBlocking {
        reviewDao.insertAll(reviewList)

        assertThat(reviewDao.count()).isEqualTo(2)

        reviewDao.clearAll()
        assertThat(reviewDao.count()).isEqualTo(0)
    }

    @Test
    fun count() = runBlocking {
        reviewDao.insertAll(reviewList)

        val count = reviewDao.count()
        assertThat(count).isEqualTo(2)
    }
}