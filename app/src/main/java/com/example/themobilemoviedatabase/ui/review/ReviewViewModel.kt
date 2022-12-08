package com.example.themobilemoviedatabase.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.data.repository.ReviewRepositoryImpl
import com.example.themobilemoviedatabase.data.repository.TvEpisodeDetailsRepositoryImpl
import com.example.themobilemoviedatabase.ui.episodes.detail.TvEpisodeDetailViewModel
import kotlinx.coroutines.flow.*

class ReviewViewModel(
    private val reviewRepositoryImpl: ReviewRepositoryImpl
) : ViewModel() {
    private val idFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val languageFlow: MutableStateFlow<String> = MutableStateFlow("en")
    private val mediaTypeFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val reviewIdFlow: MutableStateFlow<String> = MutableStateFlow("")

    val review = combine(
        idFlow,
        languageFlow,
        mediaTypeFlow,
    ) { (id, language, mediaType) ->
        DataPreferences(
            id = id as Int,
            language = language as String,
            mediaType = mediaType as String
        )
    }.flatMapLatest {
        reviewRepositoryImpl.getAllReviewById(
            mediaType = it.mediaType.toString(),
            id = it.id!!,
            language = it.language.toString()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = Resources.Loading()
    )

    val reviewDetail = reviewIdFlow.flatMapLatest {
        reviewRepositoryImpl.getReviewDetailById(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = Resources.Loading()
    )

    fun setReviewId(reviewId: String) {
        reviewIdFlow.value = reviewId
    }
    fun setMediaType(mediaType: String) {
        mediaTypeFlow.value = mediaType
    }

    fun setFilmId(id: Int) {
        idFlow.value = id
    }

    fun setLanguage(language: String) {
        languageFlow.tryEmit(language)
    }

    private class DataPreferences(
        val id: Int? = null,
        val language: String? = null,
        val mediaType: String? = null,
    )

    companion object {
        fun reviewViewModelFactory(repository: ReviewRepositoryImpl) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ReviewViewModel::class.java)) {
                        return ReviewViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
    }
}