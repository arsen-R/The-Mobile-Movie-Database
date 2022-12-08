package com.example.themobilemoviedatabase.domain.repository

import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.ReviewDetail
import com.example.themobilemoviedatabase.domain.model.ReviewResult
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    suspend fun getAllReviewById(
        mediaType: String,
        id: Int,
        language: String
    ): Flow<Resources<ReviewResult>>

    suspend fun getReviewDetailById(
        reviewId: String
    ): Flow<Resources<ReviewDetail>>
}