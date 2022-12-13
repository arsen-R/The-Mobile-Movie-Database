package com.example.themobilemoviedatabase.ui.favorite.favorite_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.databinding.FragmentFavoriteItemMovieBinding
import com.example.themobilemoviedatabase.domain.util.Constants
import com.example.themobilemoviedatabase.ui.adapter.FavoriteAdapter
import com.example.themobilemoviedatabase.ui.favorite.FavoriteFragmentDirections
import com.example.themobilemoviedatabase.ui.favorite.FavoriteViewModel
import kotlinx.coroutines.launch

class FavoriteItemMovieFragment : Fragment() {
    private var _binding: FragmentFavoriteItemMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels {
        FavoriteViewModel.favoriteViewModelFactory(
            (activity?.application as Application).favoriteRepository
        )
    }
    private val adapter by lazy { FavoriteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteItemMovieBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.personFilmography.adapter = adapter
        binding.personFilmography.setHasFixedSize(true)
        adapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val movie = adapter.currentList[position]

            if (movie.media_type == Constants.MOVIE_PARAMS) {
                val direction = FavoriteFragmentDirections.actionFavoriteScreenToDetailFragment(
                    filmId = movie.id!!,
                    movieTitle = movie.title!!
                )
                findNavController().navigate(direction)
            } else {
                val direction =
                    FavoriteFragmentDirections.actionFavoriteScreenToDetailsTvShowFragment(
                        tvShowId = movie.id!!,
                        tvShowTitle = movie.title!!
                    )
                findNavController().navigate(direction)
            }
        }
        arguments?.takeIf {
            it.containsKey("tab").apply {
                val requiredTab = it.getInt("tab")
                if (requiredTab == 1) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.getAllMovie().collect { movie ->
                            adapter.submitList(movie)
                        }
                    }
                }
                if (requiredTab == 2) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.getAllTvShow().collect { tv ->
                            adapter.submitList(tv)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}