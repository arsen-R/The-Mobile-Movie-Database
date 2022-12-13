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
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.databinding.FragmentPersonBinding
import com.example.themobilemoviedatabase.domain.model.Person
import com.example.themobilemoviedatabase.domain.util.Constants
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL
import com.example.themobilemoviedatabase.ui.adapter.PersonImageAdapter
import com.example.themobilemoviedatabase.ui.adapter.PosterFilmographyAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PersonFragment : Fragment() {
    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonViewModel by viewModels {
        PersonViewModel.personViewModelFactory(
            (activity?.application as Application).personRepository
        )
    }
    private val personId: Int by lazy { arguments?.getInt("personId") as Int }
    private val imageAdapter: PersonImageAdapter by lazy { PersonImageAdapter() }
    private val filmographyAdapter by lazy { PosterFilmographyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentPersonBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun getLoadData() {
        viewModel.setPersonId(personId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLoadData()
        binding.personFilmographyList.popularMovieLabel.isVisible = false
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.person.collectLatest { response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.nestedScroll.isVisible = false
                    }
                    is Resources.Success -> {
                        binding.nestedScroll.isVisible = true
                        response.data?.let { person ->
                            Log.d("Fetch Person data", "$person")
                            setupPersonDetail(person)
                        }
                    }
                    is Resources.Error -> {
                        binding.nestedScroll.isVisible = false
                    }
                }
            }
        }
    }

    private fun setupPersonDetail(person: Person) {
        with(binding) {
            personImage.load(IMAGE_URL + person.profile_path) {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f))
            }
            personName.text = person.name
            personBirthday.text = person.birthday
            if (person.deathday != null) {
                personDeathDay.isVisible = true
                personDiedLabel.isVisible = true
                personDeathDay.text = person.deathday
            }
            personPlaceBirth.text = person.place_of_birth
            personBiography.text = person.biography

            imagePostersList.adapter = imageAdapter
            imagePostersList.setHasFixedSize(true)

            viewLifecycleOwner.lifecycleScope.launch {
                imageAdapter.submitList(person.images?.profiles)
            }
            binding.personFilmographyDirection.setOnClickListener { view ->
                val direction =
                    PersonFragmentDirections.actionCastFragmentToPersonFilmographyFragment(personId)
                findNavController().navigate(direction)
            }
            personFilmographyList.movieList.adapter = filmographyAdapter
            personFilmographyList.movieList.setHasFixedSize(true)
            filmographyAdapter.setOnItemClickListener { view ->
                val viewHolder = view.tag as RecyclerView.ViewHolder
                val position = viewHolder.layoutPosition

                val filmography = filmographyAdapter.currentList[position]
                if (filmography.media_type == Constants.MOVIE_PARAMS) {
                    val direction = PersonFragmentDirections.actionCastFragmentToDetailFragment2(
                        filmId = filmography.id!!,
                        movieTitle = filmography.title!!
                    )
                    findNavController().navigate(direction)
                } else if (filmography.media_type == Constants.TV_PARAM) {
                    val direction =
                        PersonFragmentDirections.actionCastFragmentToDetailsTvShowFragment(
                            tvShowId = filmography.id!!,
                            tvShowTitle = filmography.name!!
                        )
                    findNavController().navigate(direction)
                }
            }

            filmographyAdapter.submitList(person.combineCredits?.cast?.sortedWith(compareBy {
                it.vote_average
            }))

            Log.d("Fetch Combine Credits", "${person.combineCredits?.cast}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}