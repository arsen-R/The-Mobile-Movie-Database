package com.example.themobilemoviedatabase.domain.repository

import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.FilmographyResult
import com.example.themobilemoviedatabase.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getPersonById(
        personId: Int,
        language: String
    ): Flow<Resources<Person>>

    suspend fun getPersonFilmography(
        personId: Int,
        language: String
    ): Flow<Resources<FilmographyResult>>
}