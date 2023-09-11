package com.example.streammoviesapplication.presentation

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.streammoviesapplication.R
import com.example.streammoviesapplication.databinding.FragmentHomeBinding
import com.example.streammoviesapplication.presentation.adapter.TrendingMoviesAdapter
import com.example.streammoviesapplication.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var trendingMoviesAdapter : TrendingMoviesAdapter
    private val vm: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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

        setUpRV()


        lifecycleScope.launch {
            vm.movieState.collectLatest {
                when {
                    it.isLoading -> {
                        Log.d("VM_CHECK", "Loading....")
                    }
                    it.trendingMovies?.isNotEmpty() == true -> {
                        Log.d("VM_CHECK", "VM works fine in Home Fragment - ${it.trendingMovies}")
                    }
                }


            }
        }

    }

    private fun setUpRV() {
        trendingMoviesAdapter = TrendingMoviesAdapter()
        binding.rvTrendingMovies.adapter = trendingMoviesAdapter
        lifecycleScope.launch {
            vm.movieState.collectLatest {
                when {
                    it.isLoading
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}