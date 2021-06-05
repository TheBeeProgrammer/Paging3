package com.example.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.network.api.TheMovieDbService
import com.example.data.network.entities.MovieEntity
import com.example.data.network.entities.MovieResponseEntity
import retrofit2.HttpException
import java.io.IOException

private const val STARTER_PAGE = 1

class TheMovieDbPagingSource(private var theMovieDbService: TheMovieDbService) :
    PagingSource<Int, MovieEntity>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val position = params.key ?: STARTER_PAGE
        return try {
            val response = theMovieDbService.fetchMovies(position)
            val movies = response.movieList
            val nextKey = if (movies.isEmpty()) {
                null
            } else {
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / 20)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTER_PAGE) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}