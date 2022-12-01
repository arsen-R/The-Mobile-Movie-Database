package com.example.themobilemoviedatabase.data.repository

import android.util.Log
import com.example.themobilemoviedatabase.data.mappers.toMovie
import com.example.themobilemoviedatabase.data.mappers.toTvShow
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.Movie
import com.example.themobilemoviedatabase.domain.model.TvShow
import com.example.themobilemoviedatabase.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepositoryImpl(
    private val apiService: MovieApiService,
) : HomeRepository {
    override suspend fun getPopularMovies(language: String): Flow<Resources<List<Movie>>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getPopularMovies(language = language)
            emit(Resources.Success(result.results?.map { it.toMovie() }))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTrendingMovies(language: String): Flow<Resources<List<Movie>>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getTrendingMovie(language = language)
            emit(Resources.Success(result.results?.map { it.toMovie() }))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getNowPlayingMovies(language: String): Flow<Resources<List<Movie>>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getNowPlayingMovie(language = language)
            emit(Resources.Success(result.results?.map { it.toMovie() }))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUpcomingMovies(language: String): Flow<Resources<List<Movie>>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getUpcomingMovie(language = language)
            emit(Resources.Success(result.results?.map { it.toMovie() }))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPopularTvShow(language: String): Flow<Resources<List<TvShow>>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getPopularTv(language = language)
            emit(Resources.Success(result.results?.map { it.toTvShow() }))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTopRatedTvShow(language: String): Flow<Resources<List<TvShow>>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getTopRatedTv(language = language)
            emit(Resources.Success(result.results?.map { it.toTvShow() }))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getOnTheAirTvShow(language: String): Flow<Resources<List<TvShow>>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getOnTheAirTv(language = language)
            Log.d("Movie Response Data Log", "Home Popular TV ${result.results}")
            emit(Resources.Success(result.results?.map { it.toTvShow() }))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAiringTodayShow(language: String): Flow<Resources<List<TvShow>>> {
        return flow {
            emit(Resources.Loading())
            val result = apiService.getAiringTodayTv(language = language)
            emit(Resources.Success(result.results?.map { it.toTvShow() }))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(Dispatchers.IO)
    }
}