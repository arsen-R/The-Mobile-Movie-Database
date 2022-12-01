package com.example.themobilemoviedatabase.data.network.dto

import com.example.themobilemoviedatabase.domain.model.Credits
import com.example.themobilemoviedatabase.domain.model.Genre
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    val backdrop_path: String? = null,
    @SerializedName("budget")
    @Expose
    val budget: Int? = null,
    @SerializedName("genres")
    @Expose
    val genres: List<GenreDto?>? = null,
    @SerializedName("homepage")
    @Expose
    val homepage: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("imdb_id")
    @Expose
    val imdb_id: String? = null,
    @SerializedName("original_language")
    @Expose
    val original_language: String? = null,
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
    @SerializedName("revenue")
    @Expose
    val revenue: Int? = null,
    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null,
    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("tagline")
    @Expose
    val tagline: String? = null,
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
    @SerializedName("credits")
    @Expose
    val credits: CreditsDto? = null,
    @SerializedName("images")
    @Expose
    val images: ImagesDto? = null,
    @SerializedName("similar")
    @Expose
    val similar: MovieResultDto? = null
)
