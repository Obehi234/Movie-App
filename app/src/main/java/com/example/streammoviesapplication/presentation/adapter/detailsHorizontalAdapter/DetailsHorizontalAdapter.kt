package com.example.streammoviesapplication.presentation.adapter.detailsHorizontalAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.streammoviesapplication.databinding.ItemMovieDetailsBinding

class DetailsHorizontalAdapter: ListAdapter<Result, DetailsHorizontalAdapter.DetailsViewHolder>(DetailsDiffUtilCallback()) {
     inner class DetailsViewHolder (private val binding:ItemMovieDetailsBinding) : RecyclerView.ViewHolder(binding.root){


    }

    class DetailsDiffUtilCallback {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}




