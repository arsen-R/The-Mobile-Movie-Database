package com.example.themobilemoviedatabase.ui.seasons

import android.os.Bundle
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
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.databinding.FragmentTvSeasonBinding
import com.example.themobilemoviedatabase.domain.model.Season
import com.example.themobilemoviedatabase.ui.adapter.TvSeasonAdapter
import com.example.themobilemoviedatabase.ui.details.DetailMovieViewModel
import kotlinx.coroutines.launch


class TvSeasonFragment : Fragment() {
    private var _binding: FragmentTvSeasonBinding? = null
    private val binding get() = _binding!!

    private val seasonAdapter: TvSeasonAdapter by lazy { TvSeasonAdapter() }

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModel.detailViewModelFactory(
            (activity?.application as Application).detailRepositoryImpl
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvSeasonBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    private val tvShowId: Int by lazy { arguments?.getInt("tvShowId") as Int }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setFilmId(tvShowId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvDetails.collect {
                    response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.tvSeasonList.isVisible = false
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.tvSeasonList.isVisible = true
                        response.data?.let { tv ->
                            seasonAdapter.submitList(tv.seasons)
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.tvSeasonList.isVisible = false
                    }
                }
            }
        }
        setupTvSeasonRecyclerView()
            //val episodeDirections = TvSeasonFragmentDirections.actionTvSeasonFragmentToTvEpisodeFragment()
            //findNavController().navigate(episodeDirections)
    }
    private fun setupTvSeasonRecyclerView() {
        binding.tvSeasonList.adapter = seasonAdapter
        binding.tvSeasonList.setHasFixedSize(true)
        seasonAdapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val season = seasonAdapter.currentList[position]
            val episodeDirection = TvSeasonFragmentDirections
                .actionTvSeasonFragmentToTvEpisodeFragment(tvShowId = tvShowId, season.season_number!!)
            findNavController().navigate(episodeDirection)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}