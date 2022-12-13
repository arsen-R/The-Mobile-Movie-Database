package com.example.themobilemoviedatabase.domain.model

import com.example.themobilemoviedatabase.domain.model.util.BaseMovie
import com.example.themobilemoviedatabase.domain.util.Constants

data class TvShowDetail(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val credits: Credits? = null,
    val episode_run_time: List<Int>? = null,
    val first_air_date: String? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    override val id: Int? = null,
    val images: Images? = null,
    val in_production: Boolean? = null,
    val languages: List<String>? = null,
    val last_air_date: String? = null,
    val last_episode_to_air: LastEpisodeToAir? = null,
    val name: String? = null,
    val next_episode_to_air: NextEpisodeToAir? = null,
    val number_of_episodes: Int? = null,
    val number_of_seasons: Int? = null,
    val origin_country: List<String>? = null,
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val seasons: List<Season>? = null,
    val similar: TvShowResult? = null,
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
): BaseMovie(title = name, backdrops = backdrop_path, media_type = Constants.TV_PARAM, id = id)