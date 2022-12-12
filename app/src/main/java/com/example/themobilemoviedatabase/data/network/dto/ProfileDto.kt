package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("aspect_ratio")
    @Expose
    val aspect_ratio: Double? = null,
    @SerializedName("file_path")
    @Expose
    val file_path: String? = null,
    @SerializedName("height")
    @Expose
    val height: Int? = null,
    @SerializedName("vote_average")
    @Expose
    val vote_average: Double? = null,
    @SerializedName("vote_count")
    @Expose
    val vote_count: Int? = null,
    @SerializedName("width")
    @Expose
    val width: Int? = null,
)