package com.example.streammoviesapplication.presentation.tabViews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.streammoviesapplication.databinding.FragmentMoviesTabBinding
import com.example.streammoviesapplication.presentation.adapter.MovieListAdapter
import com.example.streammoviesapplication.presentation.viewmodel.tabViewModel.TabViewModel
import com.example.streammoviesapplication.utils.resource.Resource

class TabMovieListFragment : Fragment(), MovieListAdapter.OnItemClickListener {
    private var _binding: FragmentMoviesTabBinding? = null
    private val binding get() = _binding!!
    private val vm:TabViewModel by activityViewModels()
    private lateinit var tabMovieAdapter: MovieListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesTabBinding.inflate(layoutInflater, container, false)
        return binding.root
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
                    hideProgressbar()
                    binding.recyclerMovies.apply{
                        adapter = tabMovieAdapter
                    }
                    tabMovieAdapter.submitList(resource.data)
                }

                is Resource.Error -> {
                    Toast.makeText(context, "${resource.message}", Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showProgressbar()
                }

                else -> {

                }
            }
        }


    }

    private fun hideProgressbar() {
        binding.movieListPgBar.visibility = View.GONE
    }

    private fun showProgressbar() {
        binding.movieListPgBar.visibility = View.VISIBLE
    }

}