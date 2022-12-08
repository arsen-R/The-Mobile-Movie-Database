package com.example.themobilemoviedatabase

import android.app.Application
import com.example.themobilemoviedatabase.data.network.ApiService
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.repository.*

class Application : Application() {
    private val apiService: MovieApiService by lazy { ApiService.movieApiService }
    val repository: HomeRepositoryImpl by lazy { HomeRepositoryImpl(apiService) }
    val detailRepositoryImpl by lazy { DetailRepositoryImpl(apiService) }
    val tvSeasonRepository by lazy { TvSeasonRepositoryImpl(apiService) }
    val tvEpisodeDetailsRepository by lazy {
        TvEpisodeDetailsRepositoryImpl(apiService)
    }
    val reviewRepository by lazy { ReviewRepositoryImpl(apiService) }

    override fun onCreate() {
        super.onCreate()
    }
}