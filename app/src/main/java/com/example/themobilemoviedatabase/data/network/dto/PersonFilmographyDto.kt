package com.example.themobilemoviedatabase.data.network.dto

import com.example.themobilemoviedatabase.domain.model.PersonCast
import com.google.gson.annotations.SerializedName

data class PersonFilmographyDto(
    @SerializedName("cast")
    val cast: List<PersonCastDto>? = null,
)