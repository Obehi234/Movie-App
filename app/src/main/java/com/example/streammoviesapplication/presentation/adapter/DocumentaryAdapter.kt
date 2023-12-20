package com.example.streammoviesapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.streammoviesapplication.data.model.localData.DocumentaryEntity
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.databinding.MovieTabRecyclerItemBinding
import com.example.streammoviesapplication.utils.Constants

class DocumentaryAdapter(private val onItemClickListener: DocumentaryAdapter.OnItemClickListener) :
    ListAdapter<DocumentaryEntity, DocumentaryAdapter.DocumentaryViewHolder>(
        DocumentaryDiffUtilCallback()
    ) {

    interface OnItemClickListener {
        fun onItemClick(movie: DocumentaryEntity)
    }

    inner class DocumentaryViewHolder(private val binding: MovieTabRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(getItem(position))
                }
            }
        }
        fun bind(item: DocumentaryEntity) {
            binding.apply {
                movieCardBg.load(Constants.BASE_IMAGE_URL + item.poster_path)
                movieTv.text = item.original_title
            }
        }
    }

    class DocumentaryDiffUtilCallback : DiffUtil.ItemCallback<DocumentaryEntity>() {
        override fun areItemsTheSame(
            oldItem: DocumentaryEntity, newItem: DocumentaryEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DocumentaryEntity, newItem: DocumentaryEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentaryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieTabRecyclerItemBinding.inflate(inflater, parent, false)
        return DocumentaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DocumentaryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
