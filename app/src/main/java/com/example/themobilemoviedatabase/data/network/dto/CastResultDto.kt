package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CastResultDto(
    @SerializedName("cast")
    @Expose
    val cast: List<CastDto>? = null
)

