package com.example.themobilemoviedatabase.domain.repository

import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.TvEpisodeDetails
import kotlinx.coroutines.flow.Flow

interface TvEpisodeDetailsRepository {
    suspend fun getTvEpisodeDetailSById(
        tvShowId: Int,
        language: String,
        seasonNumber: Int,
        episodeNumber: Int
    ) : Flow<Resources<TvEpisodeDetails>>
}