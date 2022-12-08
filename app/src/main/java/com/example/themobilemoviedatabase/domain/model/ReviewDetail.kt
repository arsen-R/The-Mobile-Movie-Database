package com.example.themobilemoviedatabase.domain.model

data class ReviewDetail(
    val author: String? = null,
    val author_details: AuthorDetails? = null,
    val content: String? = null,
    val created_at: String? = null,
    val id: String? = null,
    val iso_639_1: String? = null,
    val media_id: Int? = null,
    val media_title: String? = null,
    val media_type: String? = null,
    val updated_at: String? = null,
    val url: String? = null,
)