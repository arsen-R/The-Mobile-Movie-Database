package com.example.themobilemoviedatabase.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.example.themobilemoviedatabase.MainActivity
import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.databinding.FragmentDetailMovieBinding
import com.example.themobilemoviedatabase.ui.home.HomeFragment
import com.example.themobilemoviedatabase.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator

class DetailMovieFragment : Fragment() {
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Title"
        with(binding) {
            moviePosterBackground.load(R.drawable.ic_launcher_background) {
                crossfade(true)
                transformations(BlurTransformation(view.context, 20f, 1f))
            }
            moviePoster.load(R.drawable.ic_launcher_foreground) {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f))
            }

            ratingButton.setOnClickListener { v ->
                Toast.makeText(view.context, "Rating 7.5/10", Toast.LENGTH_LONG).show()
                val action = DetailMovieFragmentDirections.actionDetailFragmentToReviewFragment()
                findNavController().navigate(action)
            }
            favoriteButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    favoriteButton.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(view.context,R.drawable.ic_round_favorite_24), null, null)
                } else {
                    favoriteButton.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(view.context,R.drawable.ic_round_favorite_border_24), null, null)
                }
            }
            shareButton.setOnClickListener { view ->
                Toast.makeText(view.context, "Share", Toast.LENGTH_LONG).show()
            }
            binding.castLabel.setOnClickListener {
                val castDirections = DetailMovieFragmentDirections.actionDetailFragmentToCastFragment()
                findNavController().navigate(castDirections)
            }
            binding.allSeasonText.setOnClickListener {
                val seasonDirections = DetailMovieFragmentDirections.actionDetailFragmentToTvSeasonFragment()
                findNavController().navigate(seasonDirections)
            }
            binding.similarMoviesLabel.setOnClickListener {
                val action = DetailMovieFragmentDirections.actionDetailFragmentSelf()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailMovieFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}