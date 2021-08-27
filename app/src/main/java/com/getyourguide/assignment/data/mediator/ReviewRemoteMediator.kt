package com.getyourguide.assignment.data.mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.getyourguide.assignment.data.local.AppDatabase
import com.getyourguide.assignment.data.local.entity.RemoteKeyEntity
import com.getyourguide.assignment.data.local.entity.ReviewEntity
import com.getyourguide.assignment.data.local.mapper.ReviewEntityMapper
import com.getyourguide.assignment.data.remote.AppApi
import retrofit2.HttpException
import java.io.IOException

const val DEFAULT_PAGE_INDEX = 0

@ExperimentalPagingApi
class ReviewRemoteMediator(
    private val api: AppApi,
    private val db: AppDatabase,
) : RemoteMediator<Int, ReviewEntity>() {

    private val reviewDao = db.reviewDao()
    private val remoteKeyDao = db.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ReviewEntity>,
    ): MediatorResult {
        val key = when (loadType) {
            REFRESH -> {
                if (reviewDao.count() > 0) return MediatorResult.Success(false)
                null
            }
            PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            APPEND ->{
                if (state.lastItemOrNull() == null){
                    return MediatorResult.Success(
                        endOfPaginationReached = false
                    )
                }
                getKey()
            }
        }

        try {
            if (key != null) {
                if (key.isEndReached) return MediatorResult.Success(endOfPaginationReached = true)
            }

            val page: Int = key?.nextKey ?: DEFAULT_PAGE_INDEX
            val pageSize = state.config.pageSize
            val offset = page * pageSize
            val apiResponse = api.getReviews(offset = offset,
                pageSize = pageSize)

            val reviews = apiResponse.reviews

            val endOfPaginationReached = page * pageSize >= apiResponse.totalCount

            db.withTransaction {

                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                remoteKeyDao.insert(
                    RemoteKeyEntity(
                        0,
                        prevKey = prevKey,
                        nextKey = nextKey,
                        isEndReached = endOfPaginationReached
                    )
                )
                reviewDao.insertAll(ReviewEntityMapper.fromDtoList(reviews))
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKey(): RemoteKeyEntity? {
        return remoteKeyDao.getKeyByRepoId(0)
    }
}