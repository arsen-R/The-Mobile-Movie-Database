package com.example.themobilemoviedatabase.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.data.repository.TvSeasonRepositoryImpl
import kotlinx.coroutines.flow.*

class TvEpisodeViewModel(
    private val repositoryImpl: TvSeasonRepositoryImpl
) : ViewModel() {
    private val tvShowId: MutableStateFlow<Int> = MutableStateFlow(0)
    private val language: MutableStateFlow<String> = MutableStateFlow("en")
    private val seasonNumber: MutableStateFlow<Int> = MutableStateFlow(0)

    val tvSeason = combine(
        tvShowId,
        language,
        seasonNumber
    ) { (tvShowId, language, seasonNumber) ->
        DataPreferences(
            tvShowId as Int,
            language as String,
            seasonNumber as Int
        )
    }.flatMapLatest {
        repositoryImpl.getTvShowSeasonDetailById(
            it.filmId!!,
            it.language.toString(),
            it.seasonNumber!!
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = Resources.Loading()
    )

    fun setSeasonNumber(seasonNumber: Int) {
        this.seasonNumber.value = seasonNumber
    }

    fun setFilmId(filmId: Int) {
        tvShowId.value = filmId
    }

    fun setLanguage(language: String) {
        this.language.tryEmit(language)
    }

    private class DataPreferences(
        val filmId: Int? = null,
        val language: String? = null,
        val seasonNumber: Int? = null,
    )

    companion object {
        fun tvEpisodeViewModelFactory(repository: TvSeasonRepositoryImpl) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(TvEpisodeViewModel::class.java)) {
                        return TvEpisodeViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
    }
}