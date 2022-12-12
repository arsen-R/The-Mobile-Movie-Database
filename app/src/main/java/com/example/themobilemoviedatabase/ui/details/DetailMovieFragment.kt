package com.example.themobilemoviedatabase.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
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
import com.example.themobilemoviedatabase.databinding.FragmentDetailMovieBinding
import com.example.themobilemoviedatabase.domain.model.Backdrops
import com.example.themobilemoviedatabase.domain.model.Cast
import com.example.themobilemoviedatabase.domain.model.Genre
import com.example.themobilemoviedatabase.domain.model.Movie
import com.example.themobilemoviedatabase.domain.util.Constants
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL
import com.example.themobilemoviedatabase.ui.adapter.CastAdapter
import com.example.themobilemoviedatabase.ui.adapter.ImageAdapter
import com.example.themobilemoviedatabase.ui.adapter.PosterItemAdapter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class DetailMovieFragment : Fragment() {
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModel.detailViewModelFactory(
            (activity?.application as Application).detailRepositoryImpl
        )
    }

    private val castAdapter: CastAdapter by lazy { CastAdapter() }
    private val imageAdapter: ImageAdapter by lazy { ImageAdapter() }
    private val moviePostersAdapter: PosterItemAdapter by lazy { PosterItemAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    private val movieId: Int by lazy { arguments?.getInt("film_id") as Int }
    private val movieTitle: String by lazy { arguments?.getString("movieTitle") as String }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = movieTitle

        viewModel.setFilmId(movieId)

        with(binding) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.movieDetails.collect { response ->
                    when (response) {
                        is Resources.Loading -> {
                            binding.progressCircular.isVisible = true
                            binding.nestedScroll.isVisible = false
                        }
                        is Resources.Success -> {
                            binding.progressCircular.isVisible = false
                            binding.nestedScroll.isVisible = true
                            response.data?.let { movie ->
                                setupMovieBackgroundImage(movie.backdrop_path)

                                setupMoviePoster(movie.poster_path)
                                binding.movieHeader.movieTitle.text = movie.title

                                binding.movieHeader.movieDescription.text = movie.overview

                                val category = movie.genres
                                setupGenreChipGroup(category)

                                setupCastRecyclerView(movie.credits?.cast)

                                setupImageRecyclerView(movie.images?.backdrops)

                                val rating = movie.vote_average?.toInt()
                                ratingButton.text = "$rating/10"

                                setupSimilarMovie(movie.similar?.results)
                            }
                        }
                        is Resources.Error -> {
                            binding.progressCircular.isVisible = false
                            binding.nestedScroll.isVisible = false
                        }
                    }
                }
            }

            ratingButton.setOnClickListener { v ->
                val action = DetailMovieFragmentDirections.actionDetailFragmentToReviewFragment(
                    mediaType = Constants.MOVIE_PARAMS,
                    id = movieId
                )
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

    private fun setupMoviePoster(posterPath: String?) {
        binding.movieHeader.moviePoster.load(IMAGE_URL + posterPath) {
            crossfade(true)
            transformations(RoundedCornersTransformation(15f))
        }
    }

    private fun setupMovieBackgroundImage(backdropPath: String?) {
        binding.movieHeader.moviePosterBackground.load(IMAGE_URL + backdropPath) {
            crossfade(true)
            transformations(BlurTransformation(view?.context!!, 20f, 5f))
        }
    }

    private fun setupSimilarMovie(similarMovies: List<Movie>?) {
        binding.similarMoviesList.movieList.adapter = moviePostersAdapter
        binding.similarMoviesList.movieList.setHasFixedSize(true)

        binding.similarMoviesList.popularMovieLabel.text =
            resources.getString(R.string.similar_movies)
        moviePostersAdapter.setOnItemClickListener { view ->
            val holder = view.tag as RecyclerView.ViewHolder
            val position = holder.layoutPosition

            val movie = moviePostersAdapter.currentList[position]

            val directions =
                DetailMovieFragmentDirections.actionDetailFragmentSelf(movie.id!!, movie.title!!)
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
                DetailMovieFragmentDirections.actionDetailFragmentToCastFragment(cast.id!!)
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