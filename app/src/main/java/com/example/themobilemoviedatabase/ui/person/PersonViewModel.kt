package com.example.themobilemoviedatabase.ui.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.data.repository.PersonRepositoryImpl
import kotlinx.coroutines.flow.*

class PersonViewModel(
    private val repository: PersonRepositoryImpl
) : ViewModel() {
    private val personIdFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val languageFlow: MutableStateFlow<String> = MutableStateFlow("")

    val person = combine(personIdFlow, languageFlow) { (personId, language) ->
        DataPreferences(personId = personId as Int, language = language as String)
    }.flatMapLatest {
        repository.getPersonById(personId = it.personId!!, language = it.language.toString())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = Resources.Loading()
    )
    val personFilmography = combine(personIdFlow, languageFlow) { (personId, language) ->
        DataPreferences(personId = personId as Int, language = language as String)
    }.flatMapLatest {
        repository.getPersonFilmography(personId = it.personId!!, language = it.language.toString())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = Resources.Loading()
    )

    fun setPersonId(id: Int) {
        personIdFlow.value = id
    }

    fun setLanguage(language: String) {
        languageFlow.tryEmit(language)
    }

    private class DataPreferences(
        val personId: Int? = null,
        val language: String? = null,
    )

    companion object {
        fun personViewModelFactory(repository: PersonRepositoryImpl) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
                        return PersonViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }

    }

    override fun onCleared() {
        super.onCleared()
    }
}