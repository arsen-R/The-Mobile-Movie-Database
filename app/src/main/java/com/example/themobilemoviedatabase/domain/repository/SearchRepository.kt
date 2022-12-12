package com.example.themobilemoviedatabase.domain.repository

import androidx.paging.PagingData
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.MultiSearch
import com.example.themobilemoviedatabase.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun multiSearch(
        language: String = "en",
        query: String
    ): Flow<PagingData<MultiSearch>>
}