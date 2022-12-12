package com.example.themobilemoviedatabase.data.repository

import com.example.themobilemoviedatabase.data.mappers.toFilmographyResult
import com.example.themobilemoviedatabase.data.mappers.toPerson
import com.example.themobilemoviedatabase.data.network.MovieApiService
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.FilmographyResult
import com.example.themobilemoviedatabase.domain.model.Person
import com.example.themobilemoviedatabase.domain.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PersonRepositoryImpl(
    private val movieApiService: MovieApiService
): PersonRepository {
    override suspend fun getPersonById(personId: Int, language: String): Flow<Resources<Person>> {
        return flow {
            emit(Resources.Loading())
            val result = movieApiService.getPersonDetailById(
                personId = personId,
                language = language
            ).toPerson()
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }

    override suspend fun getPersonFilmography(
        personId: Int,
        language: String
    ): Flow<Resources<FilmographyResult>> {
        return flow {
            emit(Resources.Loading())
            val result = movieApiService.getPersonFilmography(
                personId = personId,
                language = language
            ).toFilmographyResult()
            emit(Resources.Success(result))
        }.catch { exception ->
            emit(Resources.Error(exception.message.toString()))
        }.flowOn(context = Dispatchers.IO)
    }
}