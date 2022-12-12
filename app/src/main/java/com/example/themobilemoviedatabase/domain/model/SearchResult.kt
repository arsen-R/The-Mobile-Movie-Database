package com.example.themobilemoviedatabase.domain.model

data class SearchResult(
    val page: Int?,
    val results: List<MultiSearch>?,
    val total_pages: Int?,
    val total_results: Int?
)