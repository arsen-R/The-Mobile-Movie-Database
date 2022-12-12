package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonCastDto(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    val backdrop_path: String? = null,
    @SerializedName("character")
    @Expose
    val character: String? = null,
    @SerializedName("credit_id")
    @Expose
    val credit_id: String? = null,
    @SerializedName("first_ait_date")
    @Expose
    val first_air_date: String? = null,
    @SerializedName("genre_ids")
    @Expose
    val genre_ids: List<Int?>? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("media_type")
    @Expose
    val media_type: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("order")
    @Expose
    val order: Int? = null,
    @SerializedName("origin_country")
    @Expose
    val origin_country: List<String>? = null,
    @SerializedName("origin_language")
    @Expose
    val original_language: String? = null,
    @SerializedName("original_name")
    @Expose
    val original_name: String? = null,
    @SerializedName("original_title")
    @Expose
    val original_title: String? = null,
    @SerializedName("overview")
    @Expose
    val overview: String? = null,
    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null,
    @SerializedName("poster_path")
    @Expose
    val poster_path: String? = null,
    @SerializedName("release_date")
    @Expose
    val release_date: String? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("video")
    @Expose
    val video: Boolean? = null,
    @SerializedName("vote_average")
    @Expose
    val vote_average: Double? = null,
    @SerializedName("vote_count")
    @Expose
    val vote_count: Int? = null,
)