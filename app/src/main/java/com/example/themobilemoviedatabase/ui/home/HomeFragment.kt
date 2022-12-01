package com.example.themobilemoviedatabase.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.databinding.FragmentHomeBinding
import com.example.themobilemoviedatabase.domain.model.util.MovieAndTvList
import com.example.themobilemoviedatabase.ui.adapter.PostersAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.homeViewModelFactory(
            (activity?.application as Application).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val movieAdapter: PostersAdapter by lazy { PostersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeList.adapter = movieAdapter
        binding.homeList.setHasFixedSize(true)
        //viewModel.setLanguage("en")

        setupPopularMovieList()
        setupTrendingMovieList()
        setupNowPlayingMovieList()
        setupUpcomingMovieList()

        setupPopularTvShowList()
        setupTopRatedTvShowList()
        setupAiringTodayTvShowList()
        setupOnTheAirTvShowList()
    }

    private fun setupTrendingMovieList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.trendingMovie
                    .collect { response ->
                        Log.d(
                            "Movie Response Data Log",
                            "Home Movie Trending ${response.data.toString()}"
                        )
                        when (response) {
                            is Resources.Loading -> {
                                hideError()
                                binding.progressCircular.isVisible = true
                                binding.homeList.isVisible = false
                            }
                            is Resources.Success -> {
                                hideError()
                                response.data.let { movie ->
                                    hideError()
                                    binding.progressCircular.isVisible = false
                                    binding.homeList.isVisible = true
                                    movieAdapter.addData((MovieAndTvList("Trending Movie", movie!!)))
                                }
                            }
                            is Resources.Error -> {
                                showError()
                                binding.homeList.isVisible = false
                                binding.progressCircular.isVisible = false
                            }
                        }
                    }
            }

        }
    }

    private fun setupPopularMovieList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.popularMovie.collect { response ->
                Log.d(
                    "Movie Response Data Log",
                    "Home Movie Popular ${response.data.toString()}"
                )
                when (response) {
                    is Resources.Loading -> {
                        hideError()
                        binding.progressCircular.isVisible = true
                        binding.homeList.isVisible = false
                    }
                    is Resources.Success -> {
                        response.data.let { movie ->
                            binding.progressCircular.isVisible = false
                            binding.homeList.isVisible = true
                            movieAdapter.addData(MovieAndTvList("Popular Movie", movie!!))
                        }
                    }
                    is Resources.Error -> {
                        showError()
                        binding.progressCircular.isVisible = false
                        binding.homeList.isVisible = false
                    }
                }
            }
        }
    }

    private fun setupNowPlayingMovieList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.nowPlayingMovie.collect { response ->
                Log.d(
                    "Movie Response Data Log",
                    "Home Now Playing Movie ${response.data.toString()}"
                )
                when (response) {
                    is Resources.Loading -> {
                        hideError()
                        binding.progressCircular.isVisible = true
                        binding.homeList.isVisible = false
                    }
                    is Resources.Success -> {
                        hideError()
                        response.data.let { movie ->
                            binding.progressCircular.isVisible = false
                            binding.homeList.isVisible = true
                            movieAdapter.addData(MovieAndTvList("Now Playing Movie", movie!!))
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.homeList.isVisible = false
                        showError()
                    }
                }
            }
        }
    }

    private fun setupUpcomingMovieList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.upcomingMovie.collect { response ->
                Log.d("Movie Response Data Log", "Home Upcoming Movie ${response.data.toString()}")
                when (response) {
                    is Resources.Loading -> {
                        hideError()
                        binding.progressCircular.isVisible = true
                        binding.homeList.isVisible = false
                    }
                    is Resources.Success -> {
                        hideError()
                        binding.progressCircular.isVisible = false
                        binding.homeList.isVisible = true
                        response.data.let { movie ->

                            movieAdapter.addData(MovieAndTvList("Upcoming Movie", movie!!))
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.homeList.isVisible = false
                        showError()
                    }
                }
            }
        }
    }
    private fun setupPopularTvShowList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.popularTvShow.collect { response ->
                Log.d("Movie Response Data Log", "Home Popular TV ${response.data.toString()}")
                when (response) {
                    is Resources.Loading -> {
                        hideError()
                        binding.progressCircular.isVisible = true
                    }
                    is Resources.Success -> {
                        hideError()
                        binding.progressCircular.isVisible = false
                        response.data.let { tv ->
                            hideError()
                            binding.progressCircular.isVisible = false
                            binding.homeList.isVisible = true
                            movieAdapter.addData(MovieAndTvList("Popular TV", tv!!))
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.homeList.isVisible = false
                        showError()
                    }
                }
            }
        }
    }

    private fun setupTopRatedTvShowList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.topRatedTvShow.collect { response ->
                Log.d("Movie Response Data Log", "Home Top Rated TV ${response.data.toString()}")
                when (response) {
                    is Resources.Loading -> {
                        hideError()
                        binding.progressCircular.isVisible = true
                    }
                    is Resources.Success -> {
                        hideError()
                        response.data.let { tv ->
                            hideError()
                            binding.progressCircular.isVisible = false
                            binding.homeList.isVisible = true
                            movieAdapter.addData(MovieAndTvList("Top Rated TV", tv!!))
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.homeList.isVisible = false
                        showError()
                    }
                }
            }
        }
    }

    private fun setupOnTheAirTvShowList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onTheAirTvShow.collect { response ->
                Log.d("Movie Response Data Log", "Home On The Air TV ${response.data.toString()}")
                when (response) {
                    is Resources.Loading -> {
                        hideError()
                        binding.progressCircular.isVisible = true
                    }
                    is Resources.Success -> {
                        hideError()
                        response.data.let { tv ->
                            hideError()
                            binding.progressCircular.isVisible = false
                            binding.homeList.isVisible = true
                            movieAdapter.addData(MovieAndTvList("On The Air TV", tv!!))
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.homeList.isVisible = false
                        showError()
                    }
                }
            }
        }
    }

    private fun setupAiringTodayTvShowList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.airingTvShow.collect { response ->
                Log.d("Movie Response Data Log", "Home Airing Today TV ${response.data.toString()}")
                when (response) {
                    is Resources.Loading -> {
                        hideError()
                        binding.progressCircular.isVisible = true
                    }
                    is Resources.Success -> {
                        hideError()
                        response.data.let { tv ->
                            hideError()
                            binding.progressCircular.isVisible = false
                            binding.homeList.isVisible = true
                            movieAdapter.addData(MovieAndTvList("Airing Today TV", tv!!))
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.homeList.isVisible = false
                        showError()
                    }
                }
            }
        }
    }

    private fun hideError() {
        binding.errorMessageImage.isVisible = false
        binding.errorMessageText.isVisible = false
    }

    private fun showError() {
        binding.errorMessageImage.isVisible = true
        binding.errorMessageText.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}