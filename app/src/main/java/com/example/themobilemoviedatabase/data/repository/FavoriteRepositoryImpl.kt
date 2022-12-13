package com.example.themobilemoviedatabase.data.repository

import com.example.themobilemoviedatabase.data.database.AppDatabase
import com.example.themobilemoviedatabase.data.mappers.toMovieDetail
import com.example.themobilemoviedatabase.data.mappers.toTvShowDetail
import com.example.themobilemoviedatabase.domain.model.MovieDetail
import com.example.themobilemoviedatabase.domain.model.TvShowDetail
import com.example.themobilemoviedatabase.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteRepositoryImpl(
    private val database: AppDatabase
): FavoriteRepository {
    override fun getAllMovie(): Flow<List<MovieDetail>> {
        return flow {
            val result = database.movieDao().getSavedMovie().map { it.toMovieDetail() }
            emit(result)
        }
    }

    override fun getAllTvShow(): Flow<List<TvShowDetail>> {
        return flow {
            val result = database.movieDao().getSavedTvShow().map { it.toTvShowDetail() }
            emit(result)
        }
    }
}