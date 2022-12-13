package com.example.themobilemoviedatabase.ui.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.databinding.FragmentTvSeasonBinding
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

    private fun getLoadData() {
        viewModel.setFilmId(tvShowId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLoadData()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvDetails.collect { response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.tvSeasonList.isVisible = false
                        hideError()
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.tvSeasonList.isVisible = true
                        hideError()
                        response.data?.let { tv ->
                            seasonAdapter.submitList(tv.seasons)
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.tvSeasonList.isVisible = false
                        showError()
                    }
                }
            }
        }
        setupTvSeasonRecyclerView()
    }

    private fun hideError() {
        binding.errorMessage.errorMessageImage.isVisible = false
        binding.errorMessage.errorMessageText.isVisible = false
    }

    private fun showError() {
        binding.errorMessage.errorMessageImage.isVisible = true
        binding.errorMessage.errorMessageText.isVisible = true
    }

    private fun setupTvSeasonRecyclerView() {
        binding.tvSeasonList.adapter = seasonAdapter
        binding.tvSeasonList.setHasFixedSize(true)
        seasonAdapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val season = seasonAdapter.currentList[position]
            val episodeDirection = TvSeasonFragmentDirections
                .actionTvSeasonFragmentToTvEpisodeFragment(
                    tvShowId = tvShowId,
                    season.season_number!!
                )
            findNavController().navigate(episodeDirection)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}