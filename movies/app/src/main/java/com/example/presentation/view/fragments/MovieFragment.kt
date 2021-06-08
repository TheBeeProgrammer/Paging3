package com.example.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.Injector
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieBinding
import com.example.presentation.view.adapter.loader.MovieLoadStateAdapter
import com.example.presentation.view.adapter.movie.MovieAdapter
import com.example.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MovieFragment : Fragment(), MovieAdapter.CallBackAdapter {

    private var binding: FragmentMovieBinding? = null
    private lateinit var viewModel: MovieViewModel
    private val adapter = MovieAdapter()

    private var callBack: CallBack? = null

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieBinding.inflate(inflater, container, false)
        binding?.retryButton?.setOnClickListener { adapter.retry() }
        viewModel = ViewModelProvider(this, Injector.provideViewModelFactory(requireContext()))
            .get(MovieViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        search()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as CallBack
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
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
        adapter.setView(this)
        binding?.rvMovies?.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MovieLoadStateAdapter { adapter.retry() },
            footer = MovieLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            // show empty list
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isListEmpty)

            // Only show the list if refresh succeeds.
            binding?.rvMovies?.isVisible = loadState.mediator?.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding?.progressBar?.isVisible = loadState.mediator?.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding?.retryButton?.isVisible = loadState.mediator?.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.api_load_error, it.error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding?.emptyList?.visibility = View.VISIBLE
            binding?.rvMovies?.visibility = View.GONE
        } else {
            binding?.emptyList?.visibility = View.GONE
            binding?.rvMovies?.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun newInstance() = MovieFragment()
    }

    interface CallBack {
        fun navigateToMovieDetail(movieId: Int?)
    }

    override fun onMovieClicked(movieId: Int?) {
        callBack?.navigateToMovieDetail(movieId)
    }
}