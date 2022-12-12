package com.example.themobilemoviedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.example.themobilemoviedatabase.databinding.MovieListItemBinding
import com.example.themobilemoviedatabase.domain.model.MultiSearch
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL

class SearchAdapter: PagingDataAdapter<MultiSearch, SearchAdapter.SearchViewHolder>(SearchDiffUtil) {
    private var onClickListener: View.OnClickListener? = null

    fun setOnItemClickListener(onItemClickListener: View.OnClickListener?) {
        onClickListener = onItemClickListener
    }

    inner class SearchViewHolder(
        private val binding: MovieListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(query: MultiSearch?) {
            val backdrops = query?.poster_path ?: query?.profile_path
            binding.tvShowPoster.load(IMAGE_URL + backdrops) {
                crossfade(true)
            }
            binding.titleMovieText.text = query?.title ?: query?.name
        }
        init {
            binding.root.tag = this
            binding.root.setOnClickListener(onClickListener)
        }
    }
    private object SearchDiffUtil : DiffUtil.ItemCallback<MultiSearch>() {
        override fun areItemsTheSame(oldItem: MultiSearch, newItem: MultiSearch): Boolean {
            return oldItem.backdrop_path == newItem.backdrop_path
                    && oldItem.profile_path == newItem.profile_path
                    && oldItem.name == newItem.name
                    && oldItem.title == newItem.title
        }
        override fun areContentsTheSame(oldItem: MultiSearch, newItem: MultiSearch): Boolean {
            return oldItem.backdrop_path == newItem.backdrop_path
                    && oldItem.profile_path == newItem.profile_path
                    && oldItem.name == newItem.name
                    && oldItem.title == newItem.title
                    && oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieListItemBinding.inflate(layoutInflater,parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val query = getItem(position) ?: null
        holder.bind(query)
    }
}