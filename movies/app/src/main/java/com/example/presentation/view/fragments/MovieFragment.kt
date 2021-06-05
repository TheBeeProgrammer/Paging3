package com.example.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.Injector
import com.example.movies.databinding.FragmentMovieBinding
import com.example.presentation.view.adapter.MovieAdapter
import com.example.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MovieFragment : Fragment() {

    private var binding: FragmentMovieBinding? = null
    private lateinit var viewModel: MovieViewModel
    private val adapter = MovieAdapter()

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, Injector.provideViewModelFactory())
            .get(MovieViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        search()
    }

    private fun search() {
        // Make sure we cancel the previous job before creating a new one
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.fetchData().collectLatest { movieList ->
                adapter.submitData(movieList)
            }
        }
    }

    private fun initAdapter() {
        binding?.rvMovies?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun newInstance() = MovieFragment()
    }
}