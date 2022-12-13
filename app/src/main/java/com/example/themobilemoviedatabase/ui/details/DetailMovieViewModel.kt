package com.example.themobilemoviedatabase.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.data.repository.DetailRepositoryImpl
import com.example.themobilemoviedatabase.domain.model.MovieDetail
import com.example.themobilemoviedatabase.domain.model.TvShowDetail
import com.example.themobilemoviedatabase.domain.repository.DetailRepository
import kotlinx.coroutines.flow.*

class DetailMovieViewModel(
    private val repository: DetailRepository,
) : ViewModel() {
    private val filmIdState: MutableStateFlow<Int> = MutableStateFlow(0)
    private val languageState: MutableStateFlow<String> = MutableStateFlow("en")

    val movieDetails = combine(filmIdState, languageState) { (filmId, language) ->
        DataPreference(filmId as Int, language as String)
    }.flatMapLatest {
        repository.getMovieDetailById(it.filmId!!, it.language.toString())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = Resources.Loading()
    )

    val tvDetails = combine(filmIdState, languageState) { (tvShowId, language) ->
        DataPreference(tvShowId as Int, language as String)
    }.flatMapLatest {
        repository.getTvShowDetailById(it.filmId!!, it.language.toString())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = Resources.Loading()
    )

    fun setFilmId(filmId: Int) {
        filmIdState.value = filmId
    }

    fun setLanguage(language: String) {
        languageState.tryEmit(language)
    }


    fun insertMovie(movieDetail: MovieDetail) {
        repository.insertMovie(movieDetail)
    }

    fun checkMovieById(movieId: Int) = repository.checkMovieById(movieId)

    fun deleteMovieById(movieId: Int) {
        repository.deleteMovie(movieId)
    }

    fun checkTvShowById(tvShowId: Int) = repository.checkTvShowById(tvShowId)

    fun insertTvShow(tvShowDetail: TvShowDetail) {
        repository.insertTvShow(tvShowDetail)
    }

    fun deleteTvShowById(tvShowId: Int) {
        repository.deleteTvShow(tvShowId)
    }

    private data class DataPreference(
        val filmId: Int? = null,
        val language: String? = null
    )

    companion object {
        fun detailViewModelFactory(repository: DetailRepositoryImpl) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
                        return DetailMovieViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }

    }
}