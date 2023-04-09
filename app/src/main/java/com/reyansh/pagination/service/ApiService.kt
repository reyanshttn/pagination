package com.reyansh.pagination.service

import com.reyansh.pagination.model.ImageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/images/search")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") size: Int
    ): List<ImageModel>
}