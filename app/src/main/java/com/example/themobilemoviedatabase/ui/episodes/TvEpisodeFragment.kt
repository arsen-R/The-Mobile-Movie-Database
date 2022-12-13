package com.example.themobilemoviedatabase.ui.episodes

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
import com.example.themobilemoviedatabase.databinding.FragmentTvEpisodeBinding
import com.example.themobilemoviedatabase.ui.adapter.TvEpisodeAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TvEpisodeFragment : Fragment() {
    private var _binding: FragmentTvEpisodeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TvEpisodeViewModel by viewModels {
        TvEpisodeViewModel.tvEpisodeViewModelFactory(
            (activity?.application as Application).tvSeasonRepository
        )
    }
    private val tvShowId: Int by lazy { arguments?.getInt("tvShowId") as Int }
    private val seasonNumber: Int by lazy { arguments?.getInt("seasonNumber") as Int }
    private val adapter by lazy { TvEpisodeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvEpisodeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun getLoadData() {
        viewModel.setFilmId(tvShowId)
        viewModel.setSeasonNumber(seasonNumber)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLoadData()

        binding.tvEpisodeList.adapter = adapter
        binding.tvEpisodeList.setHasFixedSize(true)
        adapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val episode = adapter.currentList[position]

            //Toast.makeText(view.context, "$episode", Toast.LENGTH_LONG).show()

            val directions = TvEpisodeFragmentDirections
                .actionTvEpisodeFragmentToEpisodeDetailFragment(
                    tvShowId = tvShowId,
                    seasonNumber = seasonNumber,
                    episodeNumber = episode.episode_number!!
                )
            findNavController().navigate(directions)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tvSeason.collectLatest { response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.tvEpisodeList.isVisible = false
                        hideError()
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.tvEpisodeList.isVisible = true
                        hideError()
                        response.data?.let { season ->
                            adapter.submitList(season.episodes)
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.tvEpisodeList.isVisible = false
                        showError()
                    }
                }
            }
        }
    }

    private fun hideError() {
        binding.errorMessage.errorMessageImage.isVisible = false
        binding.errorMessage.errorMessageText.isVisible = false
    }

    private fun showError() {
        binding.errorMessage.errorMessageImage.isVisible = true
        binding.errorMessage.errorMessageText.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}