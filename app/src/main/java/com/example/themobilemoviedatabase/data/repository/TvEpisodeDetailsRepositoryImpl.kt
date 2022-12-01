package com.example.themobilemoviedatabase.data.repository

import android.util.Log
import com.example.themobilemoviedatabase.data.mappers.toTvEpisodeDetails
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.TvEpisodeDetails
import com.example.themobilemoviedatabase.domain.repository.TvEpisodeDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TvEpisodeDetailsRepositoryImpl(
    private val apiService: MovieApiService
): TvEpisodeDetailsRepository {

    override suspend fun getTvEpisodeDetailSById(
        tvShowId: Int,
        language: String,
        seasonNumber: Int,
        episodeNumber: Int
    ): Flow<Resources<TvEpisodeDetails>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getTvEpisodeDetailById(
                tvShowId = tvShowId,
                seasonNumber = seasonNumber,
                episodeNumber = episodeNumber,
                language = language
            ).toTvEpisodeDetails()
            Log.d("Fetch Episode Data", "Season = $seasonNumber | Episode = $episodeNumber | $result")
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }
}