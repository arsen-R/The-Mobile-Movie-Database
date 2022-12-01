package com.example.themobilemoviedatabase.data.network.dto

import com.example.themobilemoviedatabase.domain.model.TvShow
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowResultDto(
    @SerializedName("page")
    @Expose
    val page: Int? = null,
    @SerializedName("results")
    @Expose
    val results: List<TvShowDto>? = null,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null
)
