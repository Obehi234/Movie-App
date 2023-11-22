package com.example.streammoviesapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.streammoviesapplication.data.model.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.databinding.HomeNavMoviePosterItemBinding


class TrendingMoviesAdapter : ListAdapter<TrendingMoviesEntity, TrendingMoviesAdapter.MovieViewHolder>(DiffUtilCallback()){

    inner class MovieViewHolder(private val binding: HomeNavMoviePosterItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingMoviesEntity) {
            binding.apply{
                ivPoster.load("https://image.tmdb.org/t/p/w500" + item.poster_path)
                tvRatingNumber.text = item.vote_average.toString()
                tvMovieName.text = item.title
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<TrendingMoviesEntity>() {
        override fun areItemsTheSame(
            oldItem: TrendingMoviesEntity,
            newItem: TrendingMoviesEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TrendingMoviesEntity,
            newItem: TrendingMoviesEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeNavMoviePosterItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}