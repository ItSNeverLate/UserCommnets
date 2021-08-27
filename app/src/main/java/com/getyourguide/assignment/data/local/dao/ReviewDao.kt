package com.getyourguide.assignment.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.getyourguide.assignment.data.local.entity.ReviewEntity

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ReviewEntity>)

    @Query("SELECT * FROM reviews ORDER BY created DESC")
    fun getAll(): PagingSource<Int, ReviewEntity>

    @Query("DELETE FROM reviews")
    suspend fun clearAll()

    @Query("SELECT COUNT(id) FROM reviews")
    suspend fun count(): Int
}