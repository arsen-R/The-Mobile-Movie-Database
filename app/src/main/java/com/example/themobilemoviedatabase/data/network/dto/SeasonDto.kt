package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SeasonDto(
    @SerializedName("air_date")
    @Expose
    val air_date: String? = null,
    @SerializedName("episode_count")
    @Expose
    val episode_count: Int? = null,
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
