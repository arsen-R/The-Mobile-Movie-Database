package com.example.themobilemoviedatabase

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.themobilemoviedatabase.data.database.AppDatabase
import com.example.themobilemoviedatabase.data.network.ApiService
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.repository.*
import com.example.themobilemoviedatabase.ui.settings.ThemeProvider

class Application : Application() {
    private val apiService: MovieApiService by lazy { ApiService.movieApiService }
    private val movieDao by lazy { AppDatabase.getInstance(applicationContext) }
    val repository: HomeRepositoryImpl by lazy { HomeRepositoryImpl(apiService) }
    val detailRepositoryImpl by lazy { DetailRepositoryImpl(apiService, movieDao) }
    val tvSeasonRepository by lazy { TvSeasonRepositoryImpl(apiService) }
    val tvEpisodeDetailsRepository by lazy { TvEpisodeDetailsRepositoryImpl(apiService) }
    val reviewRepository by lazy { ReviewRepositoryImpl(apiService) }
    val personRepository by lazy { PersonRepositoryImpl(apiService) }
    val searchRepository by lazy { SearchRepositoryImpl(apiService) }
    val favoriteRepository by lazy { FavoriteRepositoryImpl(movieDao) }

    override fun onCreate() {
        super.onCreate()
        val theme = ThemeProvider(this).getThemeFromPreferences()
        AppCompatDelegate.setDefaultNightMode(theme)
    }
}