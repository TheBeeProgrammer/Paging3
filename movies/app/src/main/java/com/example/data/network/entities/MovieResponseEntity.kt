package com.example.data.network.entities

import com.example.domain.model.Dates
import com.example.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponseEntity(
    val dates: Dates,
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieEntity>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)