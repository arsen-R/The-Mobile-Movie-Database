package com.example.themobilemoviedatabase.ui.episodes.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.data.repository.TvEpisodeDetailsRepositoryImpl
import com.example.themobilemoviedatabase.data.repository.TvSeasonRepositoryImpl
import com.example.themobilemoviedatabase.domain.repository.TvEpisodeDetailsRepository
import com.example.themobilemoviedatabase.ui.episodes.TvEpisodeViewModel
import kotlinx.coroutines.flow.*

class TvEpisodeDetailViewModel(
    private val repository: TvEpisodeDetailsRepository
): ViewModel() {
    private val tvShowIdFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val languageFlow: MutableStateFlow<String> = MutableStateFlow("en")
    private val seasonNumberFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val episodeNumberFlow: MutableStateFlow<Int> = MutableStateFlow(0)

    val tvEpisodeDetail = combine(
        tvShowIdFlow,
        seasonNumberFlow,
        episodeNumberFlow,
        languageFlow
    ) { (tvShowId, seasonNumber, episodeNumber, language) ->
        DataPreferences(
            filmId = tvShowId as Int,
            seasonNumber = seasonNumber as Int,
            episodeNumber = episodeNumber as Int,
            language = language as String
        )
    }.flatMapLatest {
        repository.getTvEpisodeDetailSById(
            tvShowId = it.filmId!!,
            seasonNumber = it.seasonNumber!!,
            episodeNumber = it.episodeNumber!!,
            language = it.language!!
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = Resources.Loading()
    )

    val tvEpisodeCast = combine(
        tvShowIdFlow,
        seasonNumberFlow,
        episodeNumberFlow,
        languageFlow
    ) { (tvShowId, seasonNumber, episodeNumber, language) ->
        DataPreferences(
            filmId = tvShowId as Int,
            seasonNumber = seasonNumber as Int,
            episodeNumber = episodeNumber as Int,
            language = language as String
        )
    }.flatMapLatest {
        repository.getTvEpisodeCastById(
            tvShowId = it.filmId!!,
            seasonNumber = it.seasonNumber!!,
            episodeNumber = it.episodeNumber!!,
            language = it.language!!
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = Resources.Loading()
    )
    val tvEpisodeImage = combine(
        tvShowIdFlow,
        seasonNumberFlow,
        episodeNumberFlow,
        languageFlow
    ) { (tvShowId, seasonNumber, episodeNumber, language) ->
        DataPreferences(
            filmId = tvShowId as Int,
            seasonNumber = seasonNumber as Int,
            episodeNumber = episodeNumber as Int,
            language = language as String
        )
    }.flatMapLatest {
        repository.getTvEpisodeImageById(
            tvShowId = it.filmId!!,
            seasonNumber = it.seasonNumber!!,
            episodeNumber = it.episodeNumber!!,
            language = it.language!!
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = Resources.Loading()
    )

    fun setSeasonNumber(seasonNumber: Int) {
        seasonNumberFlow.value = seasonNumber
    }

    fun setFilmId(filmId: Int) {
        tvShowIdFlow.value = filmId
    }

    fun setLanguage(language: String) {
        languageFlow.tryEmit(language)
    }
    fun setEpisodeNumber(episodeNumber: Int) {
        episodeNumberFlow.value = episodeNumber
    }

    private class DataPreferences(
        val filmId: Int? = null,
        val language: String? = null,
        val seasonNumber: Int? = null,
        val episodeNumber: Int? = null,
    )

    companion object {
        fun tvEpisodeDetailsViewModelFactory(repository: TvEpisodeDetailsRepositoryImpl) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(TvEpisodeDetailViewModel::class.java)) {
                        return TvEpisodeDetailViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("Fetch Episode Data", "Destroy ViewModel!")
    }
}