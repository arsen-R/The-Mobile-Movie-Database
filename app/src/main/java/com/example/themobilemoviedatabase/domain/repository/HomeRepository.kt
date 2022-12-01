package com.example.themobilemoviedatabase.domain.repository

import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.data.network.dto.MovieDto
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.Movie
import com.example.themobilemoviedatabase.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPopularMovies(language: String): Flow<Resources<List<Movie>>>

    suspend fun getTrendingMovies(language: String): Flow<Resources<List<Movie>>>

    suspend fun getNowPlayingMovies(language: String): Flow<Resources<List<Movie>>>

    suspend fun getUpcomingMovies(language: String): Flow<Resources<List<Movie>>>

    suspend fun getPopularTvShow(language: String): Flow<Resources<List<TvShow>>>

    suspend fun getTopRatedTvShow(language: String): Flow<Resources<List<TvShow>>>

    suspend fun getOnTheAirTvShow(language: String): Flow<Resources<List<TvShow>>>

    suspend fun getAiringTodayShow(language: String): Flow<Resources<List<TvShow>>>
}