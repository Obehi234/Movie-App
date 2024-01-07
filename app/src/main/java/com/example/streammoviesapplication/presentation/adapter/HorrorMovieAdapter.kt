package com.example.streammoviesapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.streammoviesapplication.data.model.localData.HorrorMoviesEntity
import com.example.streammoviesapplication.data.model.localData.TVSeriesEntity
import com.example.streammoviesapplication.databinding.MovieTabRecyclerItemBinding
import com.example.streammoviesapplication.utils.Constants

class HorrorMovieAdapter(private val onItemClickListener: HorrorMovieAdapter.OnItemClickListener ): ListAdapter<HorrorMoviesEntity, HorrorMovieAdapter.HorrorMovieViewHolder>(HorrorMoviesDiffUtilCallback()) {

    interface OnItemClickListener {
        fun onItemClick(movie: HorrorMoviesEntity)
    }
    inner class HorrorMovieViewHolder(private val binding: MovieTabRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(getItem(position))
                }
            }
        }
        fun bind(item: HorrorMoviesEntity) {
            binding.apply {
                movieCardBg.load(Constants.BASE_IMAGE_URL + item.poster_path)
                movieTv.text = item.original_title
            }
        }
    }

    class HorrorMoviesDiffUtilCallback: DiffUtil.ItemCallback<HorrorMoviesEntity>() {
        override fun areItemsTheSame(
            oldItem: HorrorMoviesEntity,
            newItem: HorrorMoviesEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HorrorMoviesEntity,
            newItem: HorrorMoviesEntity
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorrorMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieTabRecyclerItemBinding.inflate(inflater, parent, false)
        return HorrorMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorrorMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}