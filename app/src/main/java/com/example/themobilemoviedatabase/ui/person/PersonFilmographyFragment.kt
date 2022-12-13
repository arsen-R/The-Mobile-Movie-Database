package com.example.themobilemoviedatabase.ui.person

import android.os.Bundle
import android.util.Log
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
import com.example.themobilemoviedatabase.databinding.FragmentPersonFilmographyBinding
import com.example.themobilemoviedatabase.domain.util.Constants
import com.example.themobilemoviedatabase.ui.adapter.PersonFilmographyAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PersonFilmographyFragment : Fragment() {
    private var _binging: FragmentPersonFilmographyBinding? = null
    private val binding get() = _binging!!

    private val viewModel: PersonViewModel by viewModels {
        PersonViewModel.personViewModelFactory(
            (activity?.application as Application).personRepository
        )
    }
    private val adapter by lazy { PersonFilmographyAdapter() }

    private val personId: Int by lazy { arguments?.getInt("personId") as Int }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binging = FragmentPersonFilmographyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun getLoadData() {
        viewModel.setPersonId(personId)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLoadData()
        binding.personFilmography.adapter = adapter
        binding.personFilmography.setHasFixedSize(true)
        adapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val filmography = adapter.currentList[position]
            if (filmography.media_type == Constants.MOVIE_PARAMS) {
                val direction = PersonFilmographyFragmentDirections
                        .actionPersonFilmographyFragmentToDetailFragment(
                        filmId = filmography.id!!,
                        movieTitle = filmography.title!!
                    )
                findNavController().navigate(direction)
            } else if (filmography.media_type == Constants.TV_PARAM) {
                val direction =
                    PersonFilmographyFragmentDirections
                        .actionPersonFilmographyFragmentToDetailsTvShowFragment(
                        tvShowId = filmography.id!!,
                        tvShowTitle = filmography.name!!
                    )
                findNavController().navigate(direction)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.personFilmography.collectLatest { response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.personFilmography.isVisible = false
                        hideError()
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.personFilmography.isVisible = true
                        hideError()
                        response.data?.let { result ->
                            adapter.submitList(result.cast?.sortedWith(compareBy {
                                it.release_date
                            }))
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.personFilmography.isVisible = false
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
        _binging = null
    }
}