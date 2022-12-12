package com.example.themobilemoviedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.themobilemoviedatabase.databinding.FavoriteListItemBinding
import com.example.themobilemoviedatabase.domain.model.util.BaseMovie
import com.example.themobilemoviedatabase.domain.util.Constants

class FavoriteAdapter: ListAdapter<BaseMovie, FavoriteAdapter.FavoriteViewHolder>(FavoriteDiffUtil) {
    inner class FavoriteViewHolder(
        private val binding: FavoriteListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: BaseMovie?) {
            binding.moviePoster.load(Constants.IMAGE_URL + movie?.backdrops) {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f))
            }
            binding.titleMovieText.text = movie?.title
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

    private object FavoriteDiffUtil : DiffUtil.ItemCallback<BaseMovie>() {
        override fun areItemsTheSame(oldItem: BaseMovie, newItem: BaseMovie): Boolean {
            return oldItem.backdrops == newItem.backdrops
                    && oldItem.title == newItem.title
                    && oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BaseMovie, newItem: BaseMovie): Boolean {
            return oldItem.backdrops == newItem.backdrops && oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavoriteListItemBinding.inflate(layoutInflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        //Log.d("Movie class", "${movie?.posterPath}, ${movie?.title} ${movie?.javaClass}")
        holder.bind(movie)
    }
}