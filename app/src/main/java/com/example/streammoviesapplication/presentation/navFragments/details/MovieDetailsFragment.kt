package com.example.streammoviesapplication.presentation.navFragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.streammoviesapplication.databinding.FragmentTabMovieDetailsBinding
import com.example.streammoviesapplication.presentation.viewmodel.MovieViewModel
import com.example.streammoviesapplication.utils.resource.Resource


class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentTabMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val vm: MovieViewModel by activityViewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backIconNav.setOnClickListener {
            findNavController().navigateUp()
        }


        val movieId = args.movieId
        Log.d("CHECK MOVIE ID", "${movieId}")
        if (movieId == -1) {
            return
        }

        vm.fetchMovieDetails(movieId)
        Log.d("CHECK VM", "${movieId}")
        vm.movieDetails.observe(viewLifecycleOwner, Observer { detailsResource ->
            when (detailsResource) {
                is Resource.Success -> {
                    val movieDetails = detailsResource.data
                    movieDetails?.let {
                        val imgUrl = "https://image.tmdb.org/t/p/w500${it.backdrop_path}"
                        binding.apply{
                            ivTabDetails.load(imgUrl)
                            tvTabDetails.text = it.original_title
                            tvClockValue.text = it.runtime.toString()
                            tvStarValue.text = it.vote_average.toString()
                            tvReleaseDate.text = it.release_date
                            textAction.text = it.genres.toString()
                            synopsisDescription.text = it.overview

                        }
                    }
                }

                is Resource.Error -> {
                    Log.d("CHECK_ERROR", "Error loading movie details: ${detailsResource.message}")
                }

                is Resource.Loading -> {
                    Log.d("CHECK_LOADING", "Loading movie details...")
                }
            }
        })

    }
    }









