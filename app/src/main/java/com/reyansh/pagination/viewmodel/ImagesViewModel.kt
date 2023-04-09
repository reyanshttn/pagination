package com.reyansh.pagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.reyansh.pagination.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImagesViewModel(private val repository: ImageRepository = ImageRepository.getInstance()) :
    ViewModel() {
    fun fetchImages(): Flow<PagingData<String>> {
        return repository.letImagesFlow()
            .map { it -> it.map { it.url } }
            .cachedIn(viewModelScope)
    }
}