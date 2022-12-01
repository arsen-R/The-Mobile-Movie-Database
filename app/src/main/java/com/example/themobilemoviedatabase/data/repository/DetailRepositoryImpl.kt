package com.example.themobilemoviedatabase.data.repository

import com.example.themobilemoviedatabase.data.mappers.toMovieDetail
import com.example.themobilemoviedatabase.data.mappers.toTvShowDetail
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.MovieDetail
import com.example.themobilemoviedatabase.domain.model.TvShowDetail
import com.example.themobilemoviedatabase.domain.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailRepositoryImpl(
    private val apiService: MovieApiService
) : DetailRepository {

    override suspend fun getMovieDetailById(
        movieId: Int,
        language: String
    ): Flow<Resources<MovieDetail>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getMovieDetailById(movieId = movieId, language = language).toMovieDetail()
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }

    override suspend fun getTvShowDetailById(
        tvShowId: Int,
        language: String
    ): Flow<Resources<TvShowDetail?>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getTvShowDetailById(tvShowId = tvShowId, language = language).toTvShowDetail()
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }
}