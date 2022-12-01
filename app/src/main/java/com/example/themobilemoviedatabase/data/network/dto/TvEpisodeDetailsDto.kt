package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvEpisodeDetailsDto(
    @SerializedName("air_date")
    @Expose
    val air_date: String? = null,
//    @SerializedName("credits")
//    @Expose
//    val credits: CreditsDto? = null,
    @SerializedName("episode_number")
    @Expose
    val episode_number: Int? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
   // @SerializedName("images")
   // @Expose
    //val images: ImagesDto? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("overview")
    @Expose
    val overview: String? = null,
    @SerializedName("production_code")
    @Expose
    val production_code: String? = null,
    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null,
    @SerializedName("season_number")
    @Expose
    val season_number: Int? = null,
    @SerializedName("still_path")
    @Expose
    val still_path: String? = null,
    @SerializedName("vote_average")
    @Expose
    val vote_average: Double? = null,
    @SerializedName("vote_count")
    @Expose
    val vote_count: Int? = null,
)