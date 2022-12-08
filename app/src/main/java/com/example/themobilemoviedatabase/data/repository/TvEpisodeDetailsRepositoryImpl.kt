package com.example.themobilemoviedatabase.data.repository

import android.util.Log
import com.example.themobilemoviedatabase.data.mappers.toCast
import com.example.themobilemoviedatabase.data.mappers.toCastResult
import com.example.themobilemoviedatabase.data.mappers.toStillResult
import com.example.themobilemoviedatabase.data.mappers.toTvEpisodeDetails
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.Cast
import com.example.themobilemoviedatabase.domain.model.CastResult
import com.example.themobilemoviedatabase.domain.model.StillsResult
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
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }

    override suspend fun getTvEpisodeCastById(
        tvShowId: Int,
        language: String,
        seasonNumber: Int,
        episodeNumber: Int
    ): Flow<Resources<CastResult>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getTvEpisodeCastById(
                tvShowId = tvShowId,
                seasonNumber = seasonNumber,
                episodeNumber = episodeNumber,
                language = language
            ).toCastResult()
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }

    override suspend fun getTvEpisodeImageById(
        tvShowId: Int,
        language: String,
        seasonNumber: Int,
        episodeNumber: Int
    ): Flow<Resources<StillsResult>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getTvEpisodeImageById(
                tvShowId = tvShowId,
                seasonNumber = seasonNumber,
                episodeNumber = episodeNumber,
                language = language
            ).toStillResult()
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }
}