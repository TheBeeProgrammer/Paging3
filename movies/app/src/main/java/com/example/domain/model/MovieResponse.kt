package com.example.domain.model

import com.example.domain.model.Dates
import com.example.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val dates: Dates,
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)