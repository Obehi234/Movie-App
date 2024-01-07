package com.example.streammoviesapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.streammoviesapplication.data.model.localData.DocumentaryEntity
import com.example.streammoviesapplication.data.model.localData.TVSeriesEntity
import com.example.streammoviesapplication.databinding.MovieTabRecyclerItemBinding
import com.example.streammoviesapplication.utils.Constants.BASE_IMAGE_URL

class TVSeriesAdapter(private val onItemClickListener: TVSeriesAdapter.OnItemClickListener): ListAdapter<TVSeriesEntity, TVSeriesAdapter.TVSeriesViewHolder>(TVSeriesDiffUtilCallback()) {

    interface OnItemClickListener {
        fun onItemClick(movie: TVSeriesEntity)
    }
    inner class TVSeriesViewHolder(private val binding: MovieTabRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(getItem(position))
                }
            }
        }
        fun bind(item: TVSeriesEntity) {
            binding.apply{
                movieCardBg.load(BASE_IMAGE_URL + item.posterPath)
                movieTv.text = item.originalName
            }
        }
    }

    class TVSeriesDiffUtilCallback: DiffUtil.ItemCallback<TVSeriesEntity>() {
        override fun areItemsTheSame(oldItem: TVSeriesEntity, newItem: TVSeriesEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TVSeriesEntity, newItem: TVSeriesEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVSeriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieTabRecyclerItemBinding.inflate(inflater, parent, false)
        return TVSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVSeriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}