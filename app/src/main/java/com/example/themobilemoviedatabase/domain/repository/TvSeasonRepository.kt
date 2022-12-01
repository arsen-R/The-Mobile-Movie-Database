package com.example.themobilemoviedatabase.domain.repository

import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.TvShowSeasonDetail
import kotlinx.coroutines.flow.Flow

interface TvSeasonRepository {
    suspend fun getTvShowSeasonDetailById(
        tvShowId: Int,
        language: String,
        seasonNumber: Int
    ): Flow<Resources<TvShowSeasonDetail>>
}