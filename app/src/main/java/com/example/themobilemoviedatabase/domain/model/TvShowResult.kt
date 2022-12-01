package com.example.themobilemoviedatabase.domain.model

data class TvShowResult(
    val page: Int? = null,
    val results: List<TvShow>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
)