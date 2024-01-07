package com.example.streammoviesapplication.presentation.tabViews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.streammoviesapplication.data.model.localData.HorrorMoviesEntity
import com.example.streammoviesapplication.databinding.FragmentSportsTabBinding
import com.example.streammoviesapplication.presentation.adapter.HorrorMovieAdapter
import com.example.streammoviesapplication.presentation.navFragments.MoviesCategoryFragmentDirections
import com.example.streammoviesapplication.presentation.viewmodel.tabViewModel.TabViewModel
import com.example.streammoviesapplication.utils.resource.Resource

class TabHorrorFragment : Fragment() {
    private var _binding: FragmentSportsTabBinding? = null
    private val binding get() = _binding!!
    private val vm: TabViewModel by activityViewModels()
    private lateinit var horrorMovieAdapter : HorrorMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSportsTabBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        horrorMovieAdapter = HorrorMovieAdapter(object: HorrorMovieAdapter.OnItemClickListener{
            override fun onItemClick(movie: HorrorMoviesEntity) {
                Log.d("MovieListAdapter", "Clicked movie: ${movie.id}")
                val action =
                    MoviesCategoryFragmentDirections.actionPlayToTabMovieDetailsFragment(movie.id)
                findNavController().navigate(action)
            }
        })
        setUpRV()
    }

    private fun setUpRV() {
        vm.horrorMoviesLiveData.observe(viewLifecycleOwner){result ->
            when(result){
                is Resource.Success -> {
                    binding.recyclerMovies.apply{
                        adapter = horrorMovieAdapter
                    }
                    horrorMovieAdapter.submitList(result.data)
                }
                is Resource.Error -> {
                    Toast.makeText(context, "${result.message}", Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    Log.d("CHECK_HORROR_MOVIES_LOADING", "Horror Movies Loading ...")
                }
            }
        }
    }

}