package com.example.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.Injector
import com.example.data.network.entities.moviedetail.MovieDetailResponse
import com.example.movies.R
import com.example.movies.databinding.FagmentMovieDetailBinding
import com.example.movies.databinding.FragmentMovieBinding
import com.example.movies.databinding.FragmentMovieDeatilBinding
import com.example.presentation.view.adapter.MovieAdapter
import com.example.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

class MovieDeatilFragment : Fragment() {

    private var binding: FagmentMovieDetailBinding? = null
    private lateinit var viewModel: MovieViewModel
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FagmentMovieDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, Injector.provideViewModelFactory())
            .get(MovieViewModel::class.java)
        search()
        return binding?.root
    }

    private fun search() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getMovieDetail(837007).collectLatest { movie ->
                renderData(movie)
            }
        }
    }

    private fun renderData(movie: MovieDetailResponse) {
        binding?.tvTitle?.text = movie.title
        binding?.tvPlot?.text = movie.overview
        binding?.tvReleaseDate?.text = movie.releaseDate
        binding?.rbMovie?.rating = viewModel.getVoteAverage(movie)
        binding?.rbMovie?.isEnabled = false
        loadImage(movie.posterPath)
    }

    private fun loadImage(url: String) {
        Glide.with(requireContext()).load(BASE_IMAGE_URL.plus(url)).into(binding?.posterMovie!!)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun newInstance() = MovieDeatilFragment()
    }
}