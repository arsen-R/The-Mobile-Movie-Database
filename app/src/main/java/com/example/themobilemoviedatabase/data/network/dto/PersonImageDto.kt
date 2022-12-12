package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonImageDto(
    @SerializedName("profiles")
    @Expose
    val profiles: List<ProfileDto?>?
)