package com.example.themobilemoviedatabase.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themobilemoviedatabase.data.mappers.toSearchResult
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.domain.model.MultiSearch
import com.example.themobilemoviedatabase.domain.util.Constants
import retrofit2.HttpException

class SearchPaging(
    private val apiService: MovieApiService,
    private val query: String,
    private val language: String
) : PagingSource<Int, MultiSearch>() {
    override fun getRefreshKey(state: PagingState<Int, MultiSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MultiSearch> {
        if (query.isBlank() || language.isBlank()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        return try {
            val pageNumber = params.key ?: Constants.INITIAL_PAGE_NUMBER
            Log.d("Fetch Page number", "Page number = $pageNumber | Page load size = ${params.loadSize}")
            val response =
                apiService.multiSearch(
                    query = query,
                    language = language,
                    page = pageNumber
                ).toSearchResult()
            val searchResult = response.results
            val prevPageNumber = if (pageNumber == Constants.INITIAL_PAGE_NUMBER) null else pageNumber - 1
            val nextPageNumber = if (searchResult?.isEmpty()!!) null else pageNumber + 1
            LoadResult.Page(searchResult, prevPageNumber, nextPageNumber)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}