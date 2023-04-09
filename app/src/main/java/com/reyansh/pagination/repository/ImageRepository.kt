package com.reyansh.pagination.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.reyansh.pagination.model.ImageModel
import com.reyansh.pagination.pagination.ImagePagingSource
import com.reyansh.pagination.service.ApiService
import kotlinx.coroutines.flow.Flow

class ImageRepository(
    private val apiService: ApiService = RemoteInjector.injectApiService(),
) {
    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
        fun getInstance() = ImageRepository()
    }

    fun letImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<ImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { ImagePagingSource(apiService) }
        ).flow
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }
}