package com.example.themobilemoviedatabase.domain.model

import com.example.themobilemoviedatabase.domain.model.util.BaseMovie
import com.example.themobilemoviedatabase.domain.util.Constants

data class MovieDetail(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val budget: Int? = null,
    val genres: List<Genre?>? = null,
    val homepage: String? = null,
    override val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    override val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val credits: Credits? = null,
    val images: Images? = null,
    val similar: MovieResult? = null
) : BaseMovie(title = title, backdrops = backdrop_path, media_type = Constants.MOVIE_PARAMS, id = id)