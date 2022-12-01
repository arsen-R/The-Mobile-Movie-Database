package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowDto(
    @SerializedName("backdrop_path")
    @Expose
    val backdrop_path: String? = null,
    @SerializedName("first_air_date")
    @Expose
    val first_air_date: String? = null,
    @SerializedName("genre_ids")
    @Expose
    val genre_ids: List<Int?>? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("origin_country")
    @Expose
    val origin_country: List<String?>? = null,
    @SerializedName("origin_language")
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
    @SerializedName("vote_average")
    @Expose
    val vote_average: Double? = null,
    @SerializedName("vote_count")
    @Expose
    val vote_count: Int? = null,
    @SerializedName("media_type")
    @Expose
    val mediaType: String? = "tv",
)
