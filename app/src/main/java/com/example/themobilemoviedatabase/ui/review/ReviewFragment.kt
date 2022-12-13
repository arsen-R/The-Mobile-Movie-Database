package com.example.themobilemoviedatabase.ui.review

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
import com.example.themobilemoviedatabase.databinding.FragmentReviewBinding
import com.example.themobilemoviedatabase.ui.adapter.ReviewAdapter
import kotlinx.coroutines.launch


class ReviewFragment : Fragment() {
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReviewViewModel by viewModels {
        ReviewViewModel.reviewViewModelFactory(
            (activity?.application as Application).reviewRepository
        )
    }
    private val adapter: ReviewAdapter by lazy { ReviewAdapter() }

    private val movieId by lazy { arguments?.getInt("id") as Int }
    private val mediaType by lazy { arguments?.getString("mediaType") as String }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    private fun getLoadData() {
        viewModel.setMediaType(mediaType)
        viewModel.setFilmId(movieId)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Review Data Fetch", "Media Type = $mediaType | Id = $id")
        getLoadData()
        binding.reviewList.adapter = adapter
        binding.reviewList.setHasFixedSize(true)
        adapter.setOnItemClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.layoutPosition

            val review = adapter.currentList[position]

            val direction =
                ReviewFragmentDirections.actionReviewFragmentToReviewDetailFragment(review.id!!)
            findNavController().navigate(direction)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.review.collect { response ->
                when (response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.reviewList.isVisible = false
                        hideError()
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.reviewList.isVisible = true
                        hideError()
                        response.data?.let { review ->
                            Log.d("Review Data Fetch", "${review.results}")
                            adapter.submitList(review.results)
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.reviewList.isVisible = false
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