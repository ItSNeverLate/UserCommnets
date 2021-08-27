package com.getyourguide.assignment.di.module

import android.app.Application
import androidx.room.Room
import com.getyourguide.assignment.data.local.AppDatabase
import com.getyourguide.assignment.data.local.AppDatabase.Companion.DB_NAME
import com.getyourguide.assignment.data.remote.AppApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(AppApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAppApi(retrofit: Retrofit): AppApi =
        retrofit.create(AppApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, DB_NAME)
            .build()
}