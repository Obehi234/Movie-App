package com.example.streammoviesapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.streammoviesapplication.data.model.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.databinding.HomeNavMoviePosterItemBinding


class MovieViewPagerAdapter(private val imageList: List<TrendingMoviesEntity>) : RecyclerView.Adapter<MovieViewPagerAdapter.ViewPagerHolder>(){

    inner class ViewPagerHolder(val binding: HomeNavMoviePosterItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingMoviesEntity) {
            binding.apply{
                ivPoster.load("https://image.tmdb.org/t/p/w500" + item.poster_path)
                tvRatingNumber.text = item.vote_average.toString()
                tvMovieName.text = item.title
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeNavMoviePosterItemBinding.inflate(inflater, parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}