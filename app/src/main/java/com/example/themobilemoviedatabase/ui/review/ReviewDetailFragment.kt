package com.example.themobilemoviedatabase.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.themobilemoviedatabase.Application
import com.example.themobilemoviedatabase.data.network.utils.Resources
import com.example.themobilemoviedatabase.databinding.FragmentReviewDetailBinding
import com.example.themobilemoviedatabase.domain.util.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ReviewDetailFragment : Fragment() {
    private var _binding: FragmentReviewDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReviewViewModel by viewModels {
        ReviewViewModel.reviewViewModelFactory(
            (activity?.application as Application).reviewRepository
        )
    }
    private val reviewId: String by lazy { arguments?.getString("reviewId") as String }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReviewDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setReviewId(reviewId)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.reviewDetail.collectLatest { response ->
                when(response) {
                    is Resources.Loading -> {
                        binding.progressCircular.isVisible = true
                        binding.nestedScroll.isVisible = false
                    }
                    is Resources.Success -> {
                        binding.progressCircular.isVisible = false
                        binding.nestedScroll.isVisible = true
                        response.data?.let { review ->
                            binding.accountAvatarImage.load(Constants.IMAGE_URL + review.author_details?.avatar_path) {
                                crossfade(true)
                                transformations(CircleCropTransformation())
                            }
                            binding.accountUsername.text = review.author
                            binding.createdDateReview.text = review.created_at
                            binding.reviewText.text = review.content
                        }
                    }
                    is Resources.Error -> {
                        binding.progressCircular.isVisible = false
                        binding.nestedScroll.isVisible = false
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