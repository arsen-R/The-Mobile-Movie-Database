package com.example.themobilemoviedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.themobilemoviedatabase.databinding.CastListItemBinding
import com.example.themobilemoviedatabase.domain.model.Cast
import com.example.themobilemoviedatabase.domain.util.Constants.IMAGE_URL

class CastAdapter : ListAdapter<Cast, CastAdapter.CastItemViewHolder>(CastDiffUtil){
    private var onClickListener: View.OnClickListener? = null

    fun setOnItemClickListener(onItemClickListener: View.OnClickListener?) {
        onClickListener = onItemClickListener
    }

    inner class CastItemViewHolder(
        private val binding: CastListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast?) {
            binding.castImage.load(IMAGE_URL + cast?.profile_path) {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f))
            }
            binding.castName.text = cast?.name
            binding.castCharacter.text = cast?.character
        }
        init {
            binding.root.tag = this
            binding.root.setOnClickListener(onClickListener)
        }
    }
    private object CastDiffUtil : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.name == newItem.name && oldItem.profile_path == newItem.profile_path
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CastListItemBinding.inflate(layoutInflater, parent, false)
        return CastItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastItemViewHolder, position: Int) {
        val cast = getItem(position) ?: null
        holder.bind(cast)
    }
}