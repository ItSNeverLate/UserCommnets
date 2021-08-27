package com.getyourguide.assignment.data.repository

import com.getyourguide.assignment.data.remote.AppApi
import com.getyourguide.assignment.data.remote.response.ReviewResponse
import com.getyourguide.assignment.util.JsonUtil
import com.google.gson.Gson

class FakeAppApi : AppApi {
    private val gson = Gson()
    override suspend fun getReviews(offset: Int, pageSize: Int, sortBy: String?): ReviewResponse {
        return gson.fromJson(JsonUtil.getJsonFileContent("resources/json/review/reviews_response.json"),
            ReviewResponse::class.java)
    }
}