package com.example.streammoviesapplication.presentation.adapter.relatedMoviesHorizontalAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.streammoviesapplication.data.model.localData.RelatedMoviesEntity
import com.example.streammoviesapplication.databinding.ItemRelatedMoviesBinding
import com.example.streammoviesapplication.utils.Constants.BASE_IMAGE_URL


class RelatedMoviesAdapter :
    ListAdapter<RelatedMoviesEntity, RelatedMoviesAdapter.RelatedMoviesViewHolder>(
        RelatedMoviesDiffUtilCallback()
    ) {

    inner class RelatedMoviesViewHolder(private val binding: ItemRelatedMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RelatedMoviesEntity) {
            binding.apply {
                imgHorizontalRv.load(BASE_IMAGE_URL + item.poster_path)
                tvTitle.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRelatedMoviesBinding.inflate(inflater, parent, false)
        return RelatedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelatedMoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class RelatedMoviesDiffUtilCallback : DiffUtil.ItemCallback<RelatedMoviesEntity>() {
    override fun areItemsTheSame(
        oldItem: RelatedMoviesEntity,
        newItem: RelatedMoviesEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RelatedMoviesEntity,
        newItem: RelatedMoviesEntity
    ): Boolean {
        return oldItem == newItem
    }
}

