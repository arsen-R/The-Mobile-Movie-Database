package com.example.themobilemoviedatabase.data.repository

import com.example.themobilemoviedatabase.data.database.AppDatabase
import com.example.themobilemoviedatabase.data.mappers.toMovieDetail
import com.example.themobilemoviedatabase.data.mappers.toMovieDetailEntity
import com.example.themobilemoviedatabase.data.mappers.toTvShowDetail
import com.example.themobilemoviedatabase.data.mappers.toTvShowDetailEntity
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.MovieDetail
import com.example.themobilemoviedatabase.domain.model.TvShowDetail
import com.example.themobilemoviedatabase.domain.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class DetailRepositoryImpl(
    private val apiService: MovieApiService,
    private val database: AppDatabase
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

    override fun checkMovieById(movieId: Int): Int {
        return database.movieDao().checkSavedMovieById(movieId)
    }

    override fun insertMovie(movieDetail: MovieDetail) {
       database.movieDao().insertMovieDetail(movieDetail.toMovieDetailEntity())
    }

    override fun deleteMovie(movieId: Int) {
        database.movieDao().deleteMovieDetail(movieId)
    }

    override fun checkTvShowById(tvShowId: Int): Int {
        return database.movieDao().checkSavedTvShowById(tvShowId)
    }

    override fun insertTvShow(tvShowDetail: TvShowDetail) {
        database.movieDao().insertTvShowDetail(tvShowDetail.toTvShowDetailEntity())
    }

    override fun deleteTvShow(tvShowId: Int) {
        database.movieDao().deleteTvShowDetail(tvShowId)
    }
}