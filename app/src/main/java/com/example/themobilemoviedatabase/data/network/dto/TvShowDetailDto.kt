package com.example.themobilemoviedatabase.data.network.dto

import com.example.themobilemoviedatabase.domain.model.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowDetailDto(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    val backdrop_path: String? = null,
    @SerializedName("credits")
    @Expose
    val credits: CreditsDto? = null,
    @SerializedName("episode_run_time")
    @Expose
    val episode_run_time: List<Int>? = null,
    @SerializedName("first_air_date")
    @Expose
    val first_air_date: String? = null,
    @SerializedName("genres")
    @Expose
    val genres: List<GenreDto>? = null,
    @SerializedName("homepage")
    @Expose
    val homepage: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("images")
    @Expose
    val images: Images? = null,
    @SerializedName("in_production")
    @Expose
    val in_production: Boolean? = null,
    @SerializedName("language")
    @Expose
    val languages: List<String>? = null,
    @SerializedName("last_air_date")
    @Expose
    val last_air_date: String? = null,
    @SerializedName("last_episode_to_air")
    @Expose
    val last_episode_to_air: LastEpisodeToAirDto? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("next_episode_to_air")
    @Expose
    val next_episode_to_air: NextEpisodeToAirDto? = null,
    @SerializedName("number_of_episodes")
    @Expose
    val number_of_episodes: Int? = null,
    @SerializedName("number_of_seasons")
    @Expose
    val number_of_seasons: Int? = null,
    @SerializedName("origin_country")
    @Expose
    val origin_country: List<String>? = null,
    @SerializedName("original_language")
    @Expose
    val original_language: String? = null,
    @SerializedName("original_name")
    @Expose
    val original_name: String? = null,
    @SerializedName("overview")
    @Expose
    val overview: String? = null,
    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null,
    @SerializedName("poster_path")
    @Expose
    val poster_path: String? = null,
    @SerializedName("seasons")
    @Expose
    val seasons: List<SeasonDto>? = null,
    @SerializedName("similar")
    @Expose
    val similar: TvShowResultDto? = null,
    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("tagline")
    @Expose
    val tagline: String? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("vote_average")
    @Expose
    val vote_average: Double? = null,
    @SerializedName("vote_count")
    @Expose
    val vote_count: Int? = null,
)
