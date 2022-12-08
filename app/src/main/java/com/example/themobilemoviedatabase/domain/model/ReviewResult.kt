package com.example.themobilemoviedatabase.domain.model


data class ReviewResult(
    val id: Int?,
    val page: Int?,
    val results: List<Review?>?,
    val total_pages: Int?,
    val total_results: Int?,
)