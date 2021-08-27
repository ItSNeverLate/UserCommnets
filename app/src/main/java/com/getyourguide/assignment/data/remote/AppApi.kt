package com.getyourguide.assignment.data.remote

import com.getyourguide.assignment.data.remote.response.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    companion object {
        const val BASE_URL = "https://travelers-api.getyourguide.com"
    }

    @GET("activities/23776/reviews")
    suspend fun getReviews(
        @Query(value = "offset") offset: Int,
        @Query(value = "limit") pageSize: Int,
        @Query(value = "sort") sortBy: String? = "date:desc",
    ): ReviewResponse
}