package com.example.themobilemoviedatabase.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themobilemoviedatabase.data.repository.FavoriteRepositoryImpl

class FavoriteViewModel(
    private val repository: FavoriteRepositoryImpl
) : ViewModel() {

    fun getAllMovie() = repository.getAllMovie()

    companion object {
        fun favoriteViewModelFactory(repository: FavoriteRepositoryImpl) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
                        return FavoriteViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }

    }
    override fun onCleared() {
        super.onCleared()
    }
}