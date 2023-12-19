package com.example.streammoviesapplication.presentation.tabViews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.streammoviesapplication.R
import com.example.streammoviesapplication.databinding.FragmentSportsTabBinding
import com.example.streammoviesapplication.presentation.adapter.HorrorMovieAdapter
import com.example.streammoviesapplication.presentation.viewmodel.tabViewModel.TabViewModel
import com.example.streammoviesapplication.utils.resource.Resource

class TabSportFragment : Fragment() {
    private var _binding: FragmentSportsTabBinding? = null
    private val binding get() = _binding!!
    private val vm: TabViewModel by activityViewModels()
    private lateinit var horrorMovieAdapter : HorrorMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSportsTabBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        horrorMovieAdapter = HorrorMovieAdapter()
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
                    Log.d("CHECK_HORROR_MOVIES_FRAGMENT", "${result.message} ...")
                    Toast.makeText(context, "${result.message}", Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    Log.d("CHECK_HORROR_MOVIES_LOADING", "Horror Movies Loading ...")
                }
            }
        }
    }

}