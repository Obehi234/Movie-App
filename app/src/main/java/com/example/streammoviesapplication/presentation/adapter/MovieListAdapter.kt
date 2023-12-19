package com.example.streammoviesapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.databinding.MovieTabRecyclerItemBinding
import com.example.streammoviesapplication.presentation.tabViews.TabMovieListFragment
import com.example.streammoviesapplication.utils.Constants.BASE_IMAGE_URL


class MovieListAdapter(
    findNavController: NavController,
    tabMovieListFragment: TabMovieListFragment
) : ListAdapter<MovieResultEntity, MovieListAdapter.MovieListViewHolder>(MovieDiffUtilCallback()) {

    inner class MovieListViewHolder(private val binding:MovieTabRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieResultEntity) {
            binding.apply{
                movieCardBg.load(BASE_IMAGE_URL + item.poster_path)
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
        val binding = MovieTabRecyclerItemBinding.inflate(inflater, parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}