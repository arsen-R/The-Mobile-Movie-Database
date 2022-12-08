package com.example.themobilemoviedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.themobilemoviedatabase.databinding.ReviewListItemBinding
import com.example.themobilemoviedatabase.domain.model.Review
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL

class ReviewAdapter : ListAdapter<Review, ReviewAdapter.ReviewViewHolder>(ReviewDiffUtil) {
    private var onClickListener: View.OnClickListener? = null

    fun setOnItemClickListener(onItemClickListener: View.OnClickListener?) {
        onClickListener = onItemClickListener
    }

    inner class ReviewViewHolder(
        private val binding: ReviewListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review?) {
            with(binding) {
                accountAvatarImage.load(IMAGE_URL + review?.author_details?.avatar_path) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                accountUsername.text = review?.author
                createdDateReview.text = review?.created_at
                reviewText.text = review?.content
            }
        }
        init {
            binding.root.tag = this
            binding.root.setOnClickListener(onClickListener)
        }
    }

    private object ReviewDiffUtil: DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id && oldItem.author == newItem.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ReviewListItemBinding.inflate(layoutInflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = getItem(position) ?: null
        holder.bind(review)
    }
}