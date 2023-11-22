package com.example.streammoviesapplication.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.databinding.MovieListItemBinding

class MovieListAdapter: ListAdapter<MovieResultEntity, MovieListAdapter.MovieListViewHolder>(MovieDiffUtilCallback()) {

    inner class MovieListViewHolder(private val binding:MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieResultEntity) {
            binding.apply{
                movieCardBg.load("https://image.tmdb.org/t/p/w500" + item.poster_path)
                movieTv.text = item.original_title
            }

        }
    }

    class MovieDiffUtilCallback : DiffUtil.ItemCallback<MovieResultEntity>() {
        override fun areItemsTheSame(oldItem: MovieResultEntity, newItem: MovieResultEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieResultEntity,
            newItem: MovieResultEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieListItemBinding.inflate(inflater, parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        Log.d("CHECK_VIEWHOLDER", "I'M IN VIEWHOLDER")
        holder.bind(getItem(position))
    }
}