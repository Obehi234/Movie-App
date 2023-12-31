package com.example.streammoviesapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.streammoviesapplication.data.model.localData.TrendingMoviesEntity
import com.example.streammoviesapplication.databinding.HomeNavMoviePosterItemBinding
import com.example.streammoviesapplication.utils.Constants.BASE_IMAGE_URL


class TrendingViewPager (private val imageList : List<TrendingMoviesEntity>, private val viewPager2: ViewPager2)
    : RecyclerView.Adapter<TrendingViewPager.CardViewHolder>(){

    class CardViewHolder(val binding: HomeNavMoviePosterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingMoviesEntity) {
            binding.apply{
                ivPoster.load(BASE_IMAGE_URL + item.poster_path)
                tvRatingNumber.text = item.vote_average.toString()
                tvMovieName.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeNavMoviePosterItemBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(imageList[position])
        if (position == imageList.size -1) {
            viewPager2.post(runnable)
        }
    }
    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun getItemCount(): Int= imageList.size
}