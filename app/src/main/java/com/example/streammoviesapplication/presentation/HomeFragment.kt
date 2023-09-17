package com.example.streammoviesapplication.presentation

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.streammoviesapplication.R
import com.example.streammoviesapplication.databinding.FragmentHomeBinding
import com.example.streammoviesapplication.presentation.adapter.TrendingViewPager
import com.example.streammoviesapplication.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: MovieViewModel by activityViewModels()
    private lateinit var viewPager2: ViewPager2
    private lateinit var vpAdapter: TrendingViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fulltext = binding.tvBoldText.text.toString()
        val spannableString = SpannableString(fulltext)
        val redColorSpan =
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.orange_icon))
        spannableString.setSpan(redColorSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvBoldText.text = spannableString

        setUpViewPager()
        setUpTransformer()
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val scale = 1 - (0.25f * kotlin.math.abs(position))
            page.scaleY = scale
        }
        viewPager2.setPageTransformer(transformer)
    }


    private fun setUpViewPager() {
        viewPager2 = binding.vpTrendingMovies
        vpAdapter = TrendingViewPager(emptyList(), viewPager2)
        viewPager2.adapter = vpAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        lifecycleScope.launch {
            vm.movieState.collect { state ->
                when {
                    state.isLoading -> {
                        showProgressbar()

                    }

                    state.errorMessage != null -> {
                        Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()

                    }

                    else -> {
                        hideProgressBar()
                        state.trendingMovies?.let { trendingMovies ->
                            vpAdapter = TrendingViewPager(trendingMovies, viewPager2)
                            viewPager2.adapter = vpAdapter


                        }

                    }

                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.pgBar.visibility = View.GONE
    }

    private fun showProgressbar() {
        binding.pgBar.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}