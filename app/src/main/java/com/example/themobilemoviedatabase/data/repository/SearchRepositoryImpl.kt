package com.example.themobilemoviedatabase.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.paging.SearchPaging
import com.example.themobilemoviedatabase.domain.model.MultiSearch
import com.example.themobilemoviedatabase.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val apiService: MovieApiService
) : SearchRepository {
    override suspend fun multiSearch(
        language: String,
        query: String
    ): Flow<PagingData<MultiSearch>> {
        return Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = true
            )
        ) {
            SearchPaging(
                apiService = apiService,
                language = language,
                query = query
            )
        }.flow
    }
}
//flow {
//            emit(Resources.Loading())
//            val result = apiService.multiSearch(
//                language = language,
//                query = query
//            ).toSearchResult()
//            emit(Resources.Success(result))
//        }.catch { exception ->
//            emit(Resources.Error(exception.message.toString()))
//        }.flowOn(context = Dispatchers.IO)