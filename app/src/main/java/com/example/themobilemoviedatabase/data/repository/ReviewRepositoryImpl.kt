package com.example.themobilemoviedatabase.data.repository

import android.util.Log
import com.example.themobilemoviedatabase.data.mappers.toReviewDetail
import com.example.themobilemoviedatabase.data.mappers.toReviewResult
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.ReviewDetail
import com.example.themobilemoviedatabase.domain.model.ReviewResult
import com.example.themobilemoviedatabase.domain.repository.ReviewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ReviewRepositoryImpl(
    private val movieApiService: MovieApiService
) : ReviewRepository {
    override suspend fun getAllReviewById(
        mediaType: String,
        id: Int,
        language: String
    ): Flow<Resources<ReviewResult>> {
        return flow {
            emit(Resources.Loading())
            val result = movieApiService.getAllReviewById(
                mediaType = mediaType,
                id = id,
                language = language
            ).toReviewResult()
            Log.d(
                "Review Data Fetch",
                "Media Type = $mediaType | Id = $id | Result = ${result.results}"
            )
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }

    override suspend fun getReviewDetailById(reviewId: String): Flow<Resources<ReviewDetail>> {
        return flow {
            emit(Resources.Loading())
            val result = movieApiService.getReviewDetailById(reviewId = reviewId).toReviewDetail()
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }
}