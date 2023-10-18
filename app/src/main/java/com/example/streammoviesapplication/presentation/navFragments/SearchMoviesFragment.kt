package com.example.streammoviesapplication.presentation.navFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.streammoviesapplication.R
import com.example.streammoviesapplication.databinding.FragmentSearchMoviesBinding
import com.example.streammoviesapplication.presentation.tabViews.DocumentaryFragment
import com.example.streammoviesapplication.presentation.tabViews.MoviesFragment
import com.example.streammoviesapplication.presentation.tabViews.SportFragment
import com.example.streammoviesapplication.presentation.tabViews.TvSeriesFragment
import com.example.streammoviesapplication.presentation.vpAdapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class SearchMoviesFragment : Fragment() {
    private var _binding: FragmentSearchMoviesBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(MoviesFragment(), "Movies")
        adapter.addFragment(TvSeriesFragment(), "TvSeries")
        adapter.addFragment(DocumentaryFragment(), "Documentary")
        adapter.addFragment(SportFragment(), "Sport")

        binding.vpSearch.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.vpSearch)
    }


}