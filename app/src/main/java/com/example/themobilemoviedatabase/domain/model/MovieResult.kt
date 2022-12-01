package com.example.themobilemoviedatabase.domain.model

data class MovieResult(
    val page: Int? = null,
    val results: List<Movie>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null,
)
