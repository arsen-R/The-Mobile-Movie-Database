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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.MainActivity
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.databinding.FragmentTvEpisodeDetailBinding
import com.example.themobilemoviedatabase.domain.model.Backdrops
import com.example.themobilemoviedatabase.domain.model.Cast
import com.example.themobilemoviedatabase.domain.model.TvEpisodeDetails
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL
import com.example.themobilemoviedatabase.ui.adapter.CastAdapter
import com.example.themobilemoviedatabase.ui.adapter.ImageAdapter
import com.example.themobilemoviedatabase.ui.details.DetailMovieFragmentDirections
import kotlinx.coroutines.launch

class TvEpisodeDetailFragment : Fragment() {
    private var _binding: FragmentTvEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    private val castAdapter: CastAdapter by lazy { CastAdapter() }
    private val imageAdapter: ImageAdapter by lazy { ImageAdapter() }

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

        setupTvEpisodeViewModel()
        setupTvEpisodeCastViewModel()
        setupTvEpisodeImageViewModel()
    }

    private fun setupTvEpisodeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvEpisodeDetail.collect { response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.nestedScroll.isVisible = false
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.nestedScroll.isVisible = true
                        response.data?.let { episode ->
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

    private fun setupTvEpisodeCastViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvEpisodeCast.collect { response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.nestedScroll.isVisible = false
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.nestedScroll.isVisible = true
                        response.data?.let { credits ->
                            setupCastRecyclerView(credits.cast)
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
    private fun setupTvEpisodeImageViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvEpisodeImage.collect { response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.nestedScroll.isVisible = false
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.nestedScroll.isVisible = true
                        response.data?.let { image ->
                            setupImageRecyclerView(image.stills)
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.nestedScroll.isVisible = false
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
            var direction = ""
            var writers = ""
            for (item in episode.crew!!) {
                if (item.job == "Director") {
                    direction += " · ${item.name}"
                }
                if(item.job == "Writer") {
                    writers += " · ${item.name}"
                }
            }
            directorName.text = direction
            writersName.text = writers
        }
    }

    private fun setupCastRecyclerView(casts: List<Cast>?) {
        binding.castsList.castRecyclerList.adapter = castAdapter
        binding.castsList.castRecyclerList.setHasFixedSize(true)
        castAdapter.submitList(casts)
        castAdapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val cast = castAdapter.currentList[position]
            val castDirections =
                TvEpisodeDetailFragmentDirections.actionEpisodeDetailFragmentToCastFragment(cast.id!!)
            findNavController().navigate(castDirections)
        }
        Log.d("Cast List Request", "$casts")
    }

    private fun setupImageRecyclerView(backdrops: List<Backdrops>?) {
        binding.imagePostersList.imageList.adapter = imageAdapter
        binding.imagePostersList.imageList.setHasFixedSize(true)
        imageAdapter.submitList(backdrops)
        Log.d("Image List Request", "$backdrops")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}