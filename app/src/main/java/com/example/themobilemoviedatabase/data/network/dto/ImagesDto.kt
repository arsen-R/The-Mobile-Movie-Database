package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("backdrops")
    @Expose
    val backdrops: List<BackdropsDto>? = null,
)
