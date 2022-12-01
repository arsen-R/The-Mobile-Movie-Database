package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CastDto(
    @SerializedName("adult")
    @Expose
    val adult: Boolean?,
    @SerializedName("cast_id")
    @Expose
    val cast_id: Int?,
    @SerializedName("character")
    @Expose
    val character: String?,
    @SerializedName("credit_id")
    @Expose
    val credit_id: String?,
    @SerializedName("gender")
    @Expose
    val gender: Int?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("known_for_department")
    @Expose
    val known_for_department: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("order")
    @Expose
    val order: Int?,
    @SerializedName("original_name")
    @Expose
    val original_name: String?,
    @SerializedName("popularity")
    @Expose
    val popularity: Double?,
    @SerializedName("profile_path")
    @Expose
    val profile_path: String?
)
