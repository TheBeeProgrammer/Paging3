package com.example.data.entities.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey @field:SerializedName("id") val id: Int,
    @field:SerializedName("backdrop_path") val backdropPath: String?,
    @field:SerializedName("original_title") val originalTitle: String?,
    @field:SerializedName("poster_path") val posterPath: String?,
    @field:SerializedName("release_date") val releaseDate: String?,
    @field:SerializedName("vote_average") val voteAverage: Double?,
    @field:SerializedName("vote_count") val voteCount: Int?,
    val overview: String?,
    )