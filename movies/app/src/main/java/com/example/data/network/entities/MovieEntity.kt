package com.example.data.network.entities

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val genre_ids: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val votAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)