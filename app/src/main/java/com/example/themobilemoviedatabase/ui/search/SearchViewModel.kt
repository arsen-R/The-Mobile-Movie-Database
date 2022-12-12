package com.example.themobilemoviedatabase.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.data.repository.SearchRepositoryImpl
import kotlinx.coroutines.flow.*

class SearchViewModel(
    private val repository: SearchRepositoryImpl
) : ViewModel() {
    private val languageFlow: MutableStateFlow<String> = MutableStateFlow("en")
    private val queryFlow: MutableStateFlow<String> = MutableStateFlow("")

    val search = combine(languageFlow, queryFlow) { (language, query) ->
        DataPreferences(
            language = language,
            query = query
        )
    }.flatMapLatest {
        repository.multiSearch(
            language = it.language.toString(),
            query = it.query.toString()
        )
    }

    fun setSearchQuery(query: String) {
        queryFlow.value = query
    }

    fun setLanguage(language: String) {
        languageFlow.tryEmit(language)
    }

    private class DataPreferences(
        val language: String? = null,
        val query: String? = null,
    )

    companion object {
        fun searchViewModelFactory(repository: SearchRepositoryImpl) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                        return SearchViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
    }
}