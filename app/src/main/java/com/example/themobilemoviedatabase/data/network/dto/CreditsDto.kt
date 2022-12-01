package com.example.themobilemoviedatabase.data.network.dto

import com.example.themobilemoviedatabase.domain.model.Cast
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreditsDto(
    @SerializedName("cast")
    @Expose
    val cast: List<CastDto>?,
)
