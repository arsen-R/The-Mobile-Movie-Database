package com.example.themobilemoviedatabase.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.databinding.FragmentSearchBinding
import com.example.themobilemoviedatabase.domain.util.Constants
import com.example.themobilemoviedatabase.ui.adapter.SearchAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels {
        SearchViewModel.searchViewModelFactory(
            (activity?.application as Application).searchRepository
        )
    }
    private val adapter by lazy { SearchAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    private var job: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Fragment lifecycle", "On View Created")
        val searchManager = (activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager)
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        binding.searchView.onActionViewExpanded()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        setupRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.search.collectLatest { response ->
                adapter.submitData(viewLifecycleOwner.lifecycle, response)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.searchListLayout.searchRecyclerList.adapter = adapter
        binding.searchListLayout.searchRecyclerList.setHasFixedSize(true)

        adapter.addLoadStateListener { state ->
            with(binding) {
                searchListLayout.searchRecyclerList.isVisible = state.refresh is LoadState.NotLoading
                //errorMessage.textErrorMessage.isVisible = state.refresh is LoadState.Error
                progressCircular.isVisible = state.refresh is LoadState.Loading
            }
        }

        adapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val searchQuery = adapter.snapshot()[position]
            if (searchQuery?.media_type == Constants.MOVIE_PARAMS) {
                val movieDirection = SearchFragmentDirections.actionSearchScreenToDetailFragment(
                    searchQuery.id!!,
                    searchQuery.title!!
                )
                findNavController().navigate(movieDirection)
            } else if (searchQuery?.media_type == Constants.TV_PARAM) {
                val tvDirections =
                    SearchFragmentDirections.actionSearchScreenToDetailsTvShowFragment(
                        searchQuery.id!!,
                        searchQuery.name!!
                    )
                findNavController().navigate(tvDirections)
            } else {
                val personDirection = SearchFragmentDirections.actionSearchScreenToCastFragment(
                    searchQuery?.id!!
                )
                findNavController().navigate(personDirection)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Fragment lifecycle", "On Start")
    }

    private fun searchQuery(query: String?) {
        job?.cancel()
        job = MainScope().launch {
            delay(1000)
            query?.let {
                viewModel.setSearchQuery(it)
                //binding.searchListLayout.searchRecyclerList.scrollToPosition(0)
                binding.searchView.clearFocus()
            }
        }
        binding.searchView.clearFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Fragment lifecycle", "On Destroy View")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.searchView?.clearFocus()
        _binding = null

    }
}