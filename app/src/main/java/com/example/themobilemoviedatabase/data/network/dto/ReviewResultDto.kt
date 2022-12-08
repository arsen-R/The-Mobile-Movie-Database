package com.example.themobilemoviedatabase.data.network.dto

import com.example.themobilemoviedatabase.domain.model.Review
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewResultDto(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("page")
    @Expose
    val page: Int? = null,
    @SerializedName("results")
    @Expose
    val results: List<ReviewDto>? = null,
    @SerializedName("total_pages")
    @Expose
    val total_pages: Int? = null,
    @SerializedName("total_results")
    @Expose
    val total_results: Int? = null,
)