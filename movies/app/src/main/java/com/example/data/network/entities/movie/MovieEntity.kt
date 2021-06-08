package com.example.data.network.entities.movie

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val votAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)