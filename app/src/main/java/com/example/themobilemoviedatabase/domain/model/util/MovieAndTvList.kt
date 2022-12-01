package com.example.themobilemoviedatabase.domain.model.util

class MovieAndTvList(
    var title: String? = null,
    var broadcastList: List<BaseMovie>? = null
) {
    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + broadcastList.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieAndTvList

        if (title != other.title) return false
        if (broadcastList != other.broadcastList) return false

        return true
    }
}