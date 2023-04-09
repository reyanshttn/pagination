package com.reyansh.pagination.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.reyansh.pagination.model.ImageModel
import com.reyansh.pagination.repository.ImageRepository.Companion.DEFAULT_PAGE_INDEX
import com.reyansh.pagination.service.ApiService
import retrofit2.HttpException
import java.io.IOException

class ImagePagingSource(var apiService: ApiService) : PagingSource<Int, ImageModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        val page = params.key ?: DEFAULT_PAGE_INDEX

        return try {
            val response = apiService.getImages(page, params.loadSize)

            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )

        } catch (exception: IOException) {
            Log.e("Paging Source", "load function called $exception")

            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e("Paging Source", "load function called $exception")
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        TODO("Not yet implemented")
    }
}