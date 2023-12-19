package com.example.streammoviesapplication.presentation.navFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.streammoviesapplication.databinding.FragmentMoviesCategoryNavBinding
import com.example.streammoviesapplication.presentation.tabViews.TabDocumentaryFragment
import com.example.streammoviesapplication.presentation.tabViews.TabMovieListFragment
import com.example.streammoviesapplication.presentation.tabViews.TabSportFragment
import com.example.streammoviesapplication.presentation.tabViews.TabTvSeriesFragment
import com.example.streammoviesapplication.presentation.vpAdapter.ViewPagerAdapter


class MoviesCategoryFragment : Fragment() {
    private var _binding: FragmentMoviesCategoryNavBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesCategoryNavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(TabMovieListFragment(), "Movies")
        adapter.addFragment(TabTvSeriesFragment(), "TvSeries")
        adapter.addFragment(TabDocumentaryFragment(), "Documentary")
        adapter.addFragment(TabSportFragment(), "Horror")

        binding.vpSearch.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.vpSearch)
    }


}