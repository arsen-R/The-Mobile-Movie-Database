package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CrewDto(
    @SerializedName("adult")
    @Expose
    val adult: Boolean?,
    @SerializedName("credit_id")
    @Expose
    val credit_id: String?,
    @SerializedName("department")
    @Expose
    val department: String?,
    @SerializedName("gender")
    @Expose
    val gender: Int?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("job")
    @Expose
    val job: String?,
    @SerializedName("known_for_department")
    @Expose
    val known_for_department: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
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