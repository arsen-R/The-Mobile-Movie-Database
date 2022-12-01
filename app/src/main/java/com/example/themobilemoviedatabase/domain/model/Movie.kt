package com.example.themobilemoviedatabase.domain.model

import com.example.themobilemoviedatabase.domain.model.util.BaseMovie
import java.io.Serializable

data class Movie(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val genre_ids: List<Int?>? = null,
    override val id: Int? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    override val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val mediaType: String? = "movie"
) : BaseMovie(poster_path, title, mediaType, id), Serializable