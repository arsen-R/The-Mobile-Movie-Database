package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MultiSearchDto(
    @SerializedName("adult")
    @Expose
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    @Expose
    val backdrop_path: String?,
    @SerializedName("first_air_date")
    @Expose
    val first_air_date: String?,
    @SerializedName("gender")
    @Expose
    val gender: Int?,
    @SerializedName("genre_ids")
    @Expose
    val genre_ids: List<Int?>?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("known_for_department")
    @Expose
    val known_for_department: String?,
    @SerializedName("media_type")
    @Expose
    val media_type: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("origin_country")
    @Expose
    val origin_country: List<String>?,
    @SerializedName("original_language")
    @Expose
    val original_language: String?,
    @SerializedName("original_name")
    @Expose
    val original_name: String?,
    @SerializedName("original_title")
    @Expose
    val original_title: String?,
    @SerializedName("overview")
    @Expose
    val overview: String?,
    @SerializedName("popularity")
    @Expose
    val popularity: Double?,
    @SerializedName("poster_path")
    @Expose
    val poster_path: String?,
    @SerializedName("profile_path")
    @Expose
    val profile_path: String?,
    @SerializedName("release")
    @Expose
    val release_date: String?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("video")
    @Expose
    val video: Boolean?,
    @SerializedName("vote_average")
    @Expose
    val vote_average: Double?,
    @SerializedName("vote_count")
    @Expose
    val vote_count: Int?
)
