package com.example.themobilemoviedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.themobilemoviedatabase.databinding.PosterListItemBinding
import com.example.themobilemoviedatabase.domain.model.Profile
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL

class PersonImageAdapter :
    ListAdapter<Profile, PersonImageAdapter.PersonImageViewHolder>(ProfileDiffUtil) {
    inner class PersonImageViewHolder(
        private val binding: PosterListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile?) {
            binding.tvShowPoster.load(IMAGE_URL + profile?.file_path) {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f))
            }
        }
    }

    private object ProfileDiffUtil : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.file_path == newItem.file_path
        }

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.file_path == newItem.file_path
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PosterListItemBinding.inflate(layoutInflater, parent, false)
        return PersonImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonImageViewHolder, position: Int) {
        val profile = getItem(position) ?: null
        holder.bind(profile)
    }
}