package com.example.themobilemoviedatabase.domain.repository

import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.MovieDetail
import com.example.themobilemoviedatabase.domain.model.TvShowDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getMovieDetailById(movieId: Int, language: String): Flow<Resources<MovieDetail?>>

    suspend fun getTvShowDetailById(tvShowId: Int, language: String): Flow<Resources<TvShowDetail?>>

    fun checkMovieById(movieId: Int): Int

    fun insertMovie(movieDetail: MovieDetail)

    fun deleteMovie(movieId: Int)

    fun checkTvShowById(tvShowId: Int): Int

    fun insertTvShow(tvShowDetail: TvShowDetail)

    fun deleteTvShow(tvShowId: Int)
}