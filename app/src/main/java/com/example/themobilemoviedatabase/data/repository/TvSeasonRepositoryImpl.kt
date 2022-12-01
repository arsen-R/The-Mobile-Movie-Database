package com.example.themobilemoviedatabase.data.repository

import com.example.themobilemoviedatabase.data.mappers.toTvShowSeasonDetail
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.TvShowSeasonDetail
import com.example.themobilemoviedatabase.domain.repository.TvSeasonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvSeasonRepositoryImpl(
    private val apiService: MovieApiService
) : TvSeasonRepository {
    override suspend fun getTvShowSeasonDetailById(
        tvShowId: Int,
        language: String,
        seasonNumber: Int
    ): Flow<Resources<TvShowSeasonDetail>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getTvShowSeasonDetailById(
                tvShowId = tvShowId,
                seasonNumber = seasonNumber,
                language = language
            ).toTvShowSeasonDetail()
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }
}