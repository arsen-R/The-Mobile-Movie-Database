package com.example.themobilemoviedatabase.domain.repository

import com.example.themobilemoviedatabase.data.network.dto.CreditsDto
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.domain.model.Credits
import com.example.themobilemoviedatabase.domain.model.MovieDetail
import com.example.themobilemoviedatabase.domain.model.TvShowDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getMovieDetailById(movieId: Int, language: String) : Flow<Resources<MovieDetail?>>

    suspend fun getTvShowDetailById(tvShowId: Int, language: String) : Flow<Resources<TvShowDetail?>>
}