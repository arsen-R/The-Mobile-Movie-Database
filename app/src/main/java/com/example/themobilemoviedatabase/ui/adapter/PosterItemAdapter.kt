package com.example.themobilemoviedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.themobilemoviedatabase.databinding.PosterListItemBinding
import com.example.themobilemoviedatabase.domain.model.util.BaseMovie
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL

class PosterItemAdapter :
    ListAdapter<BaseMovie, PosterItemAdapter.MoviePosterViewHolder>(DiffUtilCallback) {
    inner class MoviePosterViewHolder(
        private val binding: PosterListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: BaseMovie?) {
            binding.tvShowPoster.load(IMAGE_URL + movie?.posterPath) {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f))
            }
        }
        init {
            binding.root.tag = this
            binding.root.setOnClickListener(onClickListener)
        }
    }

    private var onClickListener: View.OnClickListener? = null

    fun setOnItemClickListener(onItemClickListener: View.OnClickListener?) {
        onClickListener = onItemClickListener
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<BaseMovie>() {
        override fun areItemsTheSame(oldItem: BaseMovie, newItem: BaseMovie): Boolean {
            return oldItem.posterPath == newItem.posterPath
                    && oldItem.title == newItem.title
                    && oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BaseMovie, newItem: BaseMovie): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PosterListItemBinding.inflate(layoutInflater, parent, false)
        return MoviePosterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        val movie = getItem(position)
        //Log.d("Movie class", "${movie?.posterPath}, ${movie?.title} ${movie?.javaClass}")
        holder.bind(movie)
    }
}