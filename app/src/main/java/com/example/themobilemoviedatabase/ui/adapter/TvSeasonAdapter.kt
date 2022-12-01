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
import com.example.themobilemoviedatabase.databinding.TvSeasonListItemBinding
import com.example.themobilemoviedatabase.domain.model.Season
import com.example.themobilemoviedatabase.domain.model.TvShowDetail
import com.example.themobilemoviedatabase.domain.util.Constants

class TvSeasonAdapter : ListAdapter<Season, TvSeasonAdapter.TvSeasonViewHolder>(SeasonDiffUtil) {
    private object SeasonDiffUtil : DiffUtil.ItemCallback<Season>() {
        override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.overview == newItem.overview
                    && oldItem.season_number == newItem.season_number
        }

    }
    private var onClickListener: View.OnClickListener? = null

    fun setOnItemClickListener(onItemClickListener: View.OnClickListener?) {
        onClickListener = onItemClickListener
    }

    inner class TvSeasonViewHolder(
        private val binding: TvSeasonListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(season: Season?) {
            binding.seasonName.text = season?.name

            val episodeCountText = itemView.resources.getQuantityString(
                R.plurals.episodes,
                season?.episode_count!!,
                season.episode_count
            )
            val airDate: String = season.air_date ?: "-"
            binding.airDateEpisodeCount.text = itemView.resources.getString(
                R.string.air_date_episode_count,
                airDate,
                episodeCountText
            )

            if (season.overview?.isEmpty()!!) {
                binding.tvSeasonDescription.text = itemView.resources.getString(
                    R.string.empty_season_description,
                    season.season_number,
                    airDate
                )
            } else {
                binding.tvSeasonDescription.text = season.overview
            }
            binding.tvShowPoster.load(Constants.IMAGE_URL + season.poster_path) {
                crossfade(true)
                transformations(RoundedCornersTransformation(15f, 0f, 15f, 0f))
            }
        }

        init {
            binding.root.tag = this
            binding.root.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeasonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TvSeasonListItemBinding.inflate(layoutInflater, parent, false)
        return TvSeasonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeasonViewHolder, position: Int) {
        val season = getItem(position) ?: null
        holder.bind(season)
    }
}