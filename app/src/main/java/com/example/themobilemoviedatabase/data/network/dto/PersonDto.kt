package com.example.themobilemoviedatabase.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonDto(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null,
    @SerializedName("also_known_as")
    @Expose
    val also_known_as: List<String?>? = null,
    @SerializedName("biography")
    @Expose
    val biography: String? = null,
    @SerializedName("birthday")
    @Expose
    val birthday: String? = null,
    @SerializedName("deathday")
    @Expose
    val deathday: String? = null,
    @SerializedName("gender")
    @Expose
    val gender: Int? = null,
    @SerializedName("homepage")
    @Expose
    val homepage: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("imdb_id")
    @Expose
    val imdb_id: String? = null,
    @SerializedName("known_for_department")
    @Expose
    val known_for_department: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("place_of_birth")
    @Expose
    val place_of_birth: String? = null,
    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null,
    @SerializedName("profile_path")
    @Expose
    val profile_path: String? = null,
    @SerializedName("images")
    @Expose
    val images: PersonImageDto? = null,
    @SerializedName("combined_credits")
    @Expose
    val combineCredits: PersonFilmographyDto? = null
)