package com.example.data.network.api

import com.example.data.network.entities.MovieResponseEntity
import com.example.data.network.interceptor.TheMovieDbInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("/3/movie/upcoming?")
    suspend fun fetchMovies(@Query("page") page: Int): MovieResponseEntity

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"
        fun create(): TheMovieDbService {
            val client = OkHttpClient.Builder()
                .addInterceptor(
                    TheMovieDbInterceptor()
                )
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(TheMovieDbService::class.java)
        }
    }
}