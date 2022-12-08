package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthorDetailsDto(
    @SerializedName("avatar_path")
    @Expose
    val avatar_path: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("rating")
    @Expose
    val rating: Double? = null,
    @SerializedName("username")
    @Expose
    val username: String? = null,
)