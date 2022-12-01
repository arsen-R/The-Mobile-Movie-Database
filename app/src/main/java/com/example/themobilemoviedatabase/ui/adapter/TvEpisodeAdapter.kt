package com.example.themobilemoviedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.databinding.TvEpisodeListItemBinding
import com.example.themobilemoviedatabase.databinding.TvSeasonListItemBinding
import com.example.themobilemoviedatabase.domain.model.Episode
import com.example.themobilemoviedatabase.domain.util.Constants

class TvEpisodeAdapter : ListAdapter<Episode, TvEpisodeAdapter.TvEpisodeViewHolder>(TvEpisodeDiffUtil) {
    private var onClickListener: View.OnClickListener? = null

    fun setOnItemClickListener(onItemClickListener: View.OnClickListener?) {
        onClickListener = onItemClickListener
    }

    private object TvEpisodeDiffUtil : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.overview == newItem.overview
                    && oldItem.id == newItem.id
        }
    }

    inner class TvEpisodeViewHolder(
        private val binding: TvEpisodeListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode?) {
            if (episode?.overview?.isEmpty()!!) {
                binding.tvEpisodeDescription.text = itemView.resources.getString(
                    R.string.empty_episode_description,
                    episode.season_number,
                    episode.air_date
                )
            } else {
                binding.tvEpisodeDescription.text = episode.overview
            }

            binding.episodeName.text = itemView.resources.getString(
                R.string.episode_name,
                episode.episode_number,
                episode.name
            )

            binding.tvShowPoster.load(Constants.IMAGE_URL + episode.still_path) {
                crossfade(true)
                //fallback(R.drawable.ic_round_image_24)
                transformations(RoundedCornersTransformation(15f,0f,15f,0f))
            }
        }
        init {
            binding.root.tag = this
            binding.root.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvEpisodeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TvEpisodeListItemBinding.inflate(layoutInflater, parent, false)
        return TvEpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvEpisodeViewHolder, position: Int) {
        val episode = getItem(position) ?: null
        holder.bind(episode)
    }
}