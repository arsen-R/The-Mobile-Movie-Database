package com.example.themobilemoviedatabase.domain.model

data class TvEpisodeDetails(
    val air_date: String? = null,
    val crew: List<Crew>? = null,
//    val credits: Credits? = null,
    val episode_number: Int? = null,
    val id: Int? = null,
//    val images: Images? = null,
    val name: String? = null,
    val overview: String? = null,
    val production_code: String? = null,
    val runtime: Int? = null,
    val season_number: Int? = null,
    val still_path: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
)