package com.example.data.network.entities.movie

import com.google.gson.annotations.SerializedName

data class MovieResponseEntity(
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieEntity>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)