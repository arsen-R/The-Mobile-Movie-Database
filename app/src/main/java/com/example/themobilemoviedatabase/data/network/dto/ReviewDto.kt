package com.example.themobilemoviedatabase.data.network.dto

import com.example.themobilemoviedatabase.domain.model.AuthorDetails
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("author")
    @Expose
    val author: String? = null,
    @SerializedName("author_details")
    @Expose
    val author_details: AuthorDetailsDto? = null,
    @SerializedName("content")
    @Expose
    val content: String? = null,
    @SerializedName("created_at")
    @Expose
    val created_at: String? = null,
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
)