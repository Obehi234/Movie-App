package com.example.streammoviesapplication.presentation.tabViews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.streammoviesapplication.R
import com.example.streammoviesapplication.data.model.localData.result
import com.example.streammoviesapplication.databinding.FragmentMoviesBinding
import com.example.streammoviesapplication.presentation.adapter.MovieListAdapter
import com.example.streammoviesapplication.presentation.viewmodel.tabViewModel.TabViewModel
import com.example.streammoviesapplication.utils.resource.Resource
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val vm:TabViewModel by activityViewModels()
    private lateinit var tabMovieAdapter: MovieListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabMovieAdapter = MovieListAdapter()
        setUpRV()

    }


    private fun setUpRV() {
        vm.moviesTabLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    Log.d("CHECK_RESULT", "${resource.data}")
                    hideProgressbar()
                    binding.recyclerMovies.apply{
                        adapter = tabMovieAdapter
                    }
                    tabMovieAdapter.submitList(result)
                }

                is Resource.Error -> {
                    Toast.makeText(context, "${resource.message}", Toast.LENGTH_LONG).show()
                    Log.d("CHECK_TOAST", "${resource.message}")
                }

                is Resource.Loading -> {
                    showProgressbar()
                }

                else -> {
                    Log.d("CHECK_ELSE", "I'M IN ELSE")
                }
            }
        }


    }

    private fun hideProgressbar() {
        //binding.movieListPgBar.visibility = View.GONE
    }

    private fun showProgressbar() {
        //binding.movieListPgBar.visibility = View.VISIBLE
    }

}