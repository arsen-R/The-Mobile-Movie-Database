package com.example.themobilemoviedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.themobilemoviedatabase.databinding.ImageListItemBinding
import com.example.themobilemoviedatabase.domain.model.Backdrops
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL

class ImageAdapter : ListAdapter<Backdrops, ImageAdapter.ImageViewHolder>(ImageDiffUtil){
    inner class ImageViewHolder(
        private val binding: ImageListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(backdrops: Backdrops?) {
            binding.imagePosterItem.load(IMAGE_URL + backdrops?.file_path) {
                crossfade(true)
            }
        }
    }
    private object ImageDiffUtil : DiffUtil.ItemCallback<Backdrops>() {
        override fun areItemsTheSame(oldItem: Backdrops, newItem: Backdrops): Boolean {
            return oldItem.file_path == newItem.file_path
        }

        override fun areContentsTheSame(oldItem: Backdrops, newItem: Backdrops): Boolean {
            return oldItem.file_path == newItem.file_path
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageListItemBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val backdrops = getItem(position) ?: null
        holder.bind(backdrops)
    }
}