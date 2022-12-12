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
import com.example.themobilemoviedatabase.domain.model.PersonCast
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL

class PosterFilmographyAdapter :
    ListAdapter<PersonCast, PosterFilmographyAdapter.PosterFilmographyViewHolder>(PersonCastDiffUtil) {
    private var onClickListener: View.OnClickListener? = null

    fun setOnItemClickListener(onItemClickListener: View.OnClickListener?) {
        onClickListener = onItemClickListener
    }

    inner class PosterFilmographyViewHolder(
        private val binding: PosterListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(personCast: PersonCast?) {
            binding.tvShowPoster.load(IMAGE_URL + personCast?.poster_path) {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f))
            }
        }
        init {
            binding.root.tag = this
            binding.root.setOnClickListener(onClickListener)
        }
    }

    private object PersonCastDiffUtil : DiffUtil.ItemCallback<PersonCast>() {
        override fun areItemsTheSame(oldItem: PersonCast, newItem: PersonCast): Boolean {
            return oldItem.poster_path == newItem.poster_path
                    && oldItem.name == newItem.name
                    && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PersonCast, newItem: PersonCast): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.poster_path == newItem.poster_path
                    && oldItem.name == newItem.name
                    && oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterFilmographyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PosterListItemBinding.inflate(layoutInflater, parent, false)
        return PosterFilmographyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PosterFilmographyViewHolder, position: Int) {
        val filmography = getItem(position) ?: null
        holder.bind(filmography)
    }

    private val size = 20
    override fun getItemCount(): Int {
        if (currentList.size > 20) {
            return size
        } else {
            return currentList.size
        }
    }
}