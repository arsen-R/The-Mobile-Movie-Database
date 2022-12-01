package com.example.themobilemoviedatabase.domain.model.util

import java.io.Serializable

open class BaseMovie(
    open val posterPath: String? = null,
    open val title: String? = null,
    open val media_type: String? = null,
    open val id: Int? = null
) : Serializable {


    override fun hashCode(): Int {
        var result = posterPath?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseMovie

        if (posterPath != other.posterPath) return false
        if (title != other.title) return false
        if (media_type != other.media_type) return false
        if (id != other.id) return false

        return true
    }
}