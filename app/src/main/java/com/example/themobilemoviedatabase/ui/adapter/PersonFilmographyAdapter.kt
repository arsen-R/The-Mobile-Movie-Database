package com.example.themobilemoviedatabase.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.themobilemoviedatabase.databinding.FilmographyListItemBinding
import com.example.themobilemoviedatabase.domain.model.PersonCast
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL

class PersonFilmographyAdapter :
    ListAdapter<PersonCast, PersonFilmographyAdapter.PersonFilmographyViewHolder>(PersonCastDiffUtil) {
    private var onClickListener: View.OnClickListener? = null

    fun setOnItemClickListener(onItemClickListener: View.OnClickListener?) {
        onClickListener = onItemClickListener
    }

    inner class PersonFilmographyViewHolder(
        private val binding: FilmographyListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filmography: PersonCast?) {
            with(binding) {
                tvShowPoster.load(IMAGE_URL + filmography?.backdrop_path) {
                    crossfade(true)
                }
                titleMovieText.text = filmography?.title ?: filmography?.name
                ratingMovie.text = "${filmography?.vote_average?.toInt()} / 10"
                releaseDate.text = filmography?.release_date ?: filmography?.first_air_date
                personCharacter.text = filmography?.character
            }
        }

        init {
            binding.root.tag = this
            binding.root.setOnClickListener(onClickListener)
        }
    }

    private object PersonCastDiffUtil : DiffUtil.ItemCallback<PersonCast>() {
        override fun areItemsTheSame(oldItem: PersonCast, newItem: PersonCast): Boolean {
            return oldItem.backdrop_path == newItem.backdrop_path
                    && oldItem.name == newItem.name
                    && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PersonCast, newItem: PersonCast): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.title == newItem.title
                    && oldItem.name == newItem.name
                    && oldItem.backdrop_path == newItem.backdrop_path
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonFilmographyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmographyListItemBinding.inflate(layoutInflater, parent, false)
        return PersonFilmographyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonFilmographyViewHolder, position: Int) {
        val filmography = getItem(position) ?: null
        holder.bind(filmography)
        Log.d("Fetch Filmography data", "$filmography")
    }
}