package com.getyourguide.assignment.di.module

import androidx.paging.ExperimentalPagingApi
import com.getyourguide.assignment.data.local.AppDatabase
import com.getyourguide.assignment.data.remote.AppApi
import com.getyourguide.assignment.data.repository.ReviewRepositoryImp
import com.getyourguide.assignment.domain.repository.ReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @ExperimentalPagingApi
    @Provides
    @Singleton
    fun provideReviewRepository(api: AppApi, db: AppDatabase): ReviewRepository =
        ReviewRepositoryImp(api, db)
}