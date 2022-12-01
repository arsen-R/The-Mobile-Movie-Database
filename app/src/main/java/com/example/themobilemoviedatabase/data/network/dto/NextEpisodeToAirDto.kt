package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NextEpisodeToAirDto(
    @SerializedName("air_date")
    @Expose
    val air_date: String?,
    @SerializedName("episode_number")
    @Expose
    val episode_number: Int?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("overview")
    @Expose
    val overview: String?,
    @SerializedName("production_code")
    @Expose
    val production_code: String?,
    @SerializedName("backdrop_path")
    @Expose
    val runtime: Int?,
    @SerializedName("season_number")
    @Expose
    val season_number: Int?,
    @SerializedName("show_id")
    @Expose
    val show_id: Int?,
    @SerializedName("still_path")
    @Expose
    val still_path: String?,
    @SerializedName("vote_average")
    @Expose
    val vote_average: Double?,
    @SerializedName("vote_count")
    @Expose
    val vote_count: Int?
)