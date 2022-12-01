package com.example.themobilemoviedatabase.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.data.repository.HomeRepositoryImpl
import com.example.themobilemoviedatabase.domain.model.Movie
import kotlinx.coroutines.flow.*

class HomeViewModel(private val repository: HomeRepositoryImpl) : ViewModel() {
    private val languageState: MutableStateFlow<String> = MutableStateFlow("en-US")

    val popularMovie = languageState.flatMapLatest { language ->
        repository.getPopularMovies(language)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Resources.Loading()
    )

    val trendingMovie = languageState.flatMapLatest { language ->
        repository.getTrendingMovies(language)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Resources.Loading()
    )

    val nowPlayingMovie = languageState.flatMapLatest { language ->
        repository.getNowPlayingMovies(language)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Resources.Loading()
    )

    val upcomingMovie = languageState.flatMapLatest { language ->
        repository.getUpcomingMovies(language)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Resources.Loading()
    )

    val popularTvShow = languageState.flatMapLatest { language ->
        repository.getPopularTvShow(language)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Resources.Loading()
    )

    val topRatedTvShow = languageState.flatMapLatest { language ->
        repository.getTopRatedTvShow(language)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Resources.Loading()
    )

    val onTheAirTvShow = languageState.flatMapLatest { language ->
        repository.getOnTheAirTvShow(language)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Resources.Loading()
    )

    val airingTvShow = languageState.flatMapLatest { language ->
        repository.getAiringTodayShow(language)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Resources.Loading()
    )

    fun setLanguage(language: String) {
        languageState.tryEmit(language)
    }

    companion object {
        fun homeViewModelFactory(repository: HomeRepositoryImpl) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                        return HomeViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }

    }

    override fun onCleared() {
        super.onCleared()
    }
}