package com.getyourguide.assignment.data.repository

import androidx.paging.*
import com.getyourguide.assignment.data.local.AppDatabase
import com.getyourguide.assignment.data.local.mapper.ReviewEntityMapper
import com.getyourguide.assignment.data.mediator.ReviewRemoteMediator
import com.getyourguide.assignment.data.remote.AppApi
import com.getyourguide.assignment.domain.model.Review
import com.getyourguide.assignment.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class ReviewRepositoryImp constructor(
    private val api: AppApi,
    private val db: AppDatabase,
) : ReviewRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    override fun getReviews(): Flow<PagingData<Review>> {
        val pagingConfig = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
        )
        val pagingSourceFactory = { db.reviewDao().getAll() }
        return Pager(
            config = pagingConfig,
            remoteMediator = ReviewRemoteMediator(api, db),
            pagingSourceFactory = pagingSourceFactory,
        ).flow.map { pagingData ->
            pagingData.map { entity -> ReviewEntityMapper.toModel(entity) }
        }
    } //can also return livedata
}
