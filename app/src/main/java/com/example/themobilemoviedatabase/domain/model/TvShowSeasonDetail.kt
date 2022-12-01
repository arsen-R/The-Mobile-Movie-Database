package com.example.themobilemoviedatabase.domain.model

data class TvShowSeasonDetail(
    val _id: String? = null,
    val air_date: String? = null,
    val episodes: List<Episode>? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val season_number: Int? = null,
)