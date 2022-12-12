package com.example.themobilemoviedatabase.domain.model

import com.example.themobilemoviedatabase.domain.model.util.BaseMovie
import java.io.Serializable

data class TvShow(
    val backdrop_path: String? = null,
    val first_air_date: String? = null,
    val genre_ids: List<Int?>? = null,
    override val id: Int? = null,
    val name: String? = null,
    val origin_country: List<String?>? = null,
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val mediaType: String? = "tv",
) : BaseMovie(posterPath = poster_path, title = name, media_type = mediaType, id = id), Serializable