package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StillResultDto(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("stills")
    @Expose
    val stills: List<BackdropsDto>? = null
)
