package com.example.streammoviesapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.streammoviesapplication.databinding.FragmentHomeBinding
import com.example.streammoviesapplication.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm : MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}