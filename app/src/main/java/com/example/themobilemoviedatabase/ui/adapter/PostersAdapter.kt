package com.example.themobilemoviedatabase.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.themobilemoviedatabase.databinding.PosterListBinding
import com.example.themobilemoviedatabase.domain.model.util.MovieAndTvList
import com.example.themobilemoviedatabase.domain.util.Constants
import com.example.themobilemoviedatabase.ui.adapter.PostersAdapter.CinemaViewModel
import com.example.themobilemoviedatabase.ui.home.HomeFragmentDirections

class PostersAdapter : RecyclerView.Adapter<CinemaViewModel>() {
    inner class CinemaViewModel(
        private val binding: PosterListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = PosterItemAdapter()

        fun bind(movieAndTvList: MovieAndTvList?) {
            binding.movieList.adapter = adapter
            binding.movieList.setHasFixedSize(true)
            adapter.submitList(movieAndTvList?.broadcastList)

            adapter.setOnItemClickListener { view ->
                val viewHolder = view.tag as RecyclerView.ViewHolder
                val position = viewHolder.layoutPosition

                val movie = adapter.currentList[position]

                if (movie.media_type == Constants.MOVIE_PARAMS) {
                    val movieDetailDirection = HomeFragmentDirections.actionHomeScreenToDetailFragment(movie.id!!, movie.title!!)
                    Navigation.findNavController(view).navigate(movieDetailDirection)
                }
                if (movie.media_type == Constants.TV_PARAM) {
                    val tvDetailDirections = HomeFragmentDirections.actionHomeScreenToDetailsTvShowFragment(movie.id!!, movie.title!!)
                    Navigation.findNavController(view).navigate(tvDetailDirections)
                }

                Log.d(
                    "Movie Data Click",
                    "Home ${movie?.posterPath}, ${movie?.title}, ${movie.id}, ${movie.media_type}, ${movie?.javaClass}"
                )
            }
            binding.popularMovieLabel.text = movieAndTvList?.title
        }
    }

    private val data = mutableListOf<MovieAndTvList>()

    fun addData(movieAndTvList: MovieAndTvList) {
        if (data.size < 8) {
            data.add(movieAndTvList)
            notifyItemInserted(data.size)
            data.sortWith(compareBy { it.title })
            notifyDataSetChanged()
            Log.d("Filmography list sort", "Size ${data.size} | Sorter $data")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PosterListBinding.inflate(layoutInflater, parent, false)
        return CinemaViewModel(binding)
    }

    override fun onBindViewHolder(holder: CinemaViewModel, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }
}