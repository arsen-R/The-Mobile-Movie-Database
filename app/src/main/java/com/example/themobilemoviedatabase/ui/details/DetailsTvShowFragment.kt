package com.example.themobilemoviedatabase.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.MainActivity
import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.databinding.FragmentDetailsTvShowBinding
import com.example.themobilemoviedatabase.domain.model.*
import com.example.themobilemoviedatabase.domain.util.Constants
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL
import com.example.themobilemoviedatabase.ui.adapter.CastAdapter
import com.example.themobilemoviedatabase.ui.adapter.ImageAdapter
import com.example.themobilemoviedatabase.ui.adapter.PosterItemAdapter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch


class DetailsTvShowFragment : Fragment() {
    private var _binding: FragmentDetailsTvShowBinding? = null
    private val binding get() = _binding!!

    private val tvShowId: Int by lazy { arguments?.getInt("tvShowId") as Int }
    private val tvShowTitle: String by lazy { arguments?.getString("tvShowTitle") as String }

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModel.detailViewModelFactory(
            (activity?.application as Application).detailRepositoryImpl
        )
    }

    private val castAdapter: CastAdapter by lazy { CastAdapter() }
    private val imageAdapter: ImageAdapter by lazy { ImageAdapter() }
    private val moviePostersAdapter: PosterItemAdapter by lazy { PosterItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = tvShowTitle
        viewModel.setFilmId(tvShowId)
        with(binding) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.tvDetails.collect { response ->
                    when (response) {
                        is Resources.Loading -> {
                            binding.progressCircular.isVisible = true
                            binding.nestedScroll.isVisible = false
                        }
                        is Resources.Success -> {
                            binding.progressCircular.isVisible = false
                            binding.nestedScroll.isVisible = true
                            response.data?.let { tv ->
                                setupMovieBackgroundImage(tv.backdrop_path)

                                setupMoviePoster(tv.poster_path)
                                binding.movieHeader.movieTitle.text = tv.name

                                binding.movieHeader.movieDescription.text = tv.overview

                                val category = tv.genres
                                setupGenreChipGroup(category)

                                setupCastRecyclerView(tv.credits?.cast)

                                setupImageRecyclerView(tv.images?.backdrops)

                                val rating = tv.vote_average?.toInt()
                                ratingButton.text = "$rating/10"

                                setupSimilarTvShow(tv.similar?.results)
                                val lastSeason = tv.seasons?.sortedWith(compareBy { it.air_date })?.last()
                                setupLastTvSeason(
                                    lastSeason,
                                    tv.name!!
                                )
                                setupTvShowEpisode(lastSeason)
                            }
                        }
                        is Resources.Error -> {
                            binding.progressCircular.isVisible = false
                            binding.nestedScroll.isVisible = false
                        }
                    }
                }
            }

            allSeasonText.setOnClickListener {
                val direction = DetailsTvShowFragmentDirections
                    .actionDetailsTvShowFragmentToTvSeasonFragment(tvShowId)
                findNavController().navigate(direction)
            }

            ratingButton.setOnClickListener { v ->
                Toast.makeText(view.context, "Rating 7.5/10", Toast.LENGTH_LONG).show()
                val action = DetailsTvShowFragmentDirections.actionDetailsTvShowFragmentToReviewFragment()
                findNavController().navigate(action)
            }

            favoriteButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    favoriteButton.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        ContextCompat.getDrawable(view.context, R.drawable.ic_round_favorite_24),
                        null,
                        null
                    )
                } else {
                    favoriteButton.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.ic_round_favorite_border_24
                        ),
                        null,
                        null
                    )
                }
            }

            shareButton.setOnClickListener { view ->
                Toast.makeText(view.context, "Share", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun setupTvShowEpisode(season: Season?) {
        binding.lastSeasonLayout.seasonCardView.setOnClickListener {
            val episodeDirection = DetailsTvShowFragmentDirections
                .actionDetailsTvShowFragmentToTvEpisodeFragment(
                    tvShowId = tvShowId,
                    season?.season_number!!
                )
            findNavController().navigate(episodeDirection)
        }
    }
    private fun setupMoviePoster(posterPath: String?) {
        binding.movieHeader.moviePoster.load(Constants.IMAGE_URL + posterPath) {
            crossfade(true)
            transformations(RoundedCornersTransformation(15f))
        }
    }

    private fun setupMovieBackgroundImage(backdropPath: String?) {
        binding.movieHeader.moviePosterBackground.load(Constants.IMAGE_URL + backdropPath) {
            crossfade(true)
            transformations(BlurTransformation(view?.context!!, 20f, 5f))
        }
    }

    private fun setupSimilarTvShow(similarMovies: List<TvShow>?) {
        binding.similarTvShowList.movieList.adapter = moviePostersAdapter
        binding.similarTvShowList.movieList.setHasFixedSize(true)

        binding.similarTvShowList.popularMovieLabel.text =
            resources.getString(R.string.similar_movies)
        moviePostersAdapter.setOnItemClickListener { view ->
            val holder = view.tag as RecyclerView.ViewHolder
            val position = holder.layoutPosition

            val tv = moviePostersAdapter.currentList[position]

            val directions = DetailsTvShowFragmentDirections.actionDetailsTvShowFragmentSelf(tv.id!!, tv.title!!)
            findNavController().navigate(directions)
        }

        moviePostersAdapter.submitList(similarMovies)
    }

    private fun setupCastRecyclerView(casts: List<Cast>?) {
        binding.castsList.castRecyclerList.adapter = castAdapter
        binding.castsList.castRecyclerList.setHasFixedSize(true)
        castAdapter.submitList(casts)
        castAdapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val cast = castAdapter.currentList[position]
            Toast.makeText(view.context, "${cast.name}", Toast.LENGTH_LONG).show()
            val castDirections =
                DetailsTvShowFragmentDirections.actionDetailsTvShowFragmentToCastFragment()
            findNavController().navigate(castDirections)
        }
        Log.d("Cast List Request", "$casts")
    }

    private fun setupLastTvSeason(lastSeason: Season?, tvTitle: String) {
        val seasonName = lastSeason?.name
        binding.lastSeasonLayout.seasonName.text = seasonName

        val episodeCountText = resources.getQuantityString(
            R.plurals.episodes,
            lastSeason?.episode_count!!,
            lastSeason.episode_count
        )

        binding.lastSeasonLayout.airDateEpisodeCount.text = resources.getString(
            R.string.air_date_episode_count,
            lastSeason.air_date,
            episodeCountText
        )

        if (lastSeason.overview?.isEmpty()!!) {
            binding.lastSeasonLayout.tvSeasonDescription.text = resources.getString(
                R.string.empty_season_description,
                lastSeason.season_number,
                lastSeason.air_date
            )
        } else {
            binding.lastSeasonLayout.tvSeasonDescription.text = lastSeason.overview
        }
        binding.lastSeasonLayout.tvShowPoster.load(IMAGE_URL + lastSeason.poster_path) {
            crossfade(true)
            transformations(RoundedCornersTransformation(15f,0f,15f,0f))
        }
    }

    private fun setupImageRecyclerView(backdrops: List<Backdrops>?) {
        binding.imagePostersList.imageList.adapter = imageAdapter
        binding.imagePostersList.imageList.setHasFixedSize(true)
        imageAdapter.submitList(backdrops)
        Log.d("Image List Request", "$backdrops")
    }

    private fun setupGenreChipGroup(genre: List<Genre?>?) {
        binding.movieHeader.categoryChipGroup.removeAllViews()
        for (index in genre?.indices!!) {
            val chip = Chip(view?.context)
            chip.text = genre[index]?.name
            binding.movieHeader.categoryChipGroup.addView(chip)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}