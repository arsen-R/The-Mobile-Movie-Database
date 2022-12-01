package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowSeasonDetailDto(
    @SerializedName("_id")
    @Expose
    val _id: String? = null,
    @SerializedName("air_date")
    @Expose
    val air_date: String? = null,
    @SerializedName("episodes")
    @Expose
    val episodes: List<EpisodeDto>? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("overview")
    @Expose
    val overview: String? = null,
    @SerializedName("poster_path")
    @Expose
    val poster_path: String? = null,
    @SerializedName("season_number")
    @Expose
    val season_number: Int? = null,
)