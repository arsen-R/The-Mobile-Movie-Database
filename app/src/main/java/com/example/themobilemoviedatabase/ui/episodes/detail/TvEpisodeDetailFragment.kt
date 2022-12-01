package com.example.themobilemoviedatabase.ui.episodes.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.MainActivity
import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.databinding.FragmentTvEpisodeDetailBinding
import com.example.themobilemoviedatabase.domain.model.TvEpisodeDetails
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TvEpisodeDetailFragment : Fragment() {
    private var _binding: FragmentTvEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvEpisodeDetailViewModel by viewModels {
        TvEpisodeDetailViewModel.tvEpisodeDetailsViewModelFactory(
            (activity?.application as Application).tvEpisodeDetailsRepository
        )
    }

    private val tvShowId by lazy { arguments?.getInt("tvShowId") as Int }
    private val seasonNumber by lazy { arguments?.getInt("seasonNumber") as Int }
    private val episodeNumber by lazy { arguments?.getInt("episodeNumber") as Int }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvEpisodeDetailBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fetch Episode Data", "$tvShowId | $seasonNumber | $episodeNumber")
        viewModel.setFilmId(tvShowId)
        viewModel.setSeasonNumber(seasonNumber)
        viewModel.setEpisodeNumber(episodeNumber)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvEpisodeDetail.collect { response ->
                when(response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        //binding.nestedScroll.isVisible = false
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        //binding.nestedScroll.isVisible = true
                        Log.d("Fetch Episode Data", "${response.data}")
                        response.data?.let { episode ->
                            Toast.makeText(view.context, "$episode", Toast.LENGTH_LONG).show()
                            (activity as MainActivity).supportActionBar?.title = episode.name
                            setupEpisodeScreen(episode)
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        //binding.nestedScroll.isVisible = false
                    }
                }
            }
        }
    }
    private fun setupEpisodeScreen(episode: TvEpisodeDetails) {
        with(binding) {
            tvEpisodePoster.load(IMAGE_URL + episode.still_path) {
                crossfade(true)
            }
            tvEpisodeTitle.text = episode.name
            tvEpisodeDescription.text = episode.overview

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}