package com.example.themobilemoviedatabase.domain.repository

import com.example.themobilemoviedatabase.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllMovie(): Flow<List<MovieDetail>>

}