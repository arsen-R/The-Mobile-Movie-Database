package com.example.themobilemoviedatabase.data.network.dto

data class SearchResultDto(
    val page: Int?,
    val results: List<MultiSearchDto>?,
    val total_pages: Int?,
    val total_results: Int?
)