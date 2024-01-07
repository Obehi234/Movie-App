package com.example.streammoviesapplication.presentation.tabViews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.streammoviesapplication.R
import com.example.streammoviesapplication.data.model.localData.DocumentaryEntity
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.databinding.FragmentDocumentaryTabBinding
import com.example.streammoviesapplication.presentation.adapter.DocumentaryAdapter
import com.example.streammoviesapplication.presentation.navFragments.MoviesCategoryFragmentDirections
import com.example.streammoviesapplication.presentation.viewmodel.tabViewModel.TabViewModel
import com.example.streammoviesapplication.utils.resource.Resource

class TabDocumentaryFragment : Fragment() {
    private var _binding: FragmentDocumentaryTabBinding? = null
    private val binding get() = _binding!!
    private val vm: TabViewModel by activityViewModels()
    private lateinit var documentaryAdapter: DocumentaryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentaryTabBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        documentaryAdapter = DocumentaryAdapter(object: DocumentaryAdapter.OnItemClickListener {
            override fun onItemClick(movie: DocumentaryEntity) {
                Log.d("MovieListAdapter", "Clicked movie: ${movie.id}")
                val action =
                    MoviesCategoryFragmentDirections.actionPlayToTabMovieDetailsFragment(movie.id)
                findNavController().navigate(action)
            }
        })

        setUpRV()
    }

    private fun setUpRV() {
        vm.documentaryLiveData.observe(viewLifecycleOwner) {resource ->
            when(resource) {
                is Resource.Success -> {
                    binding.recyclerMovies.apply{
                        adapter = documentaryAdapter
                    }
                    documentaryAdapter.submitList(resource.data)
                }
                is Resource.Error -> {
                    Toast.makeText(context, "${resource.message}", Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    Log.d("CHECK_TV_LOADING", "TV Series Loading ...")
                }
            }
        }
    }



}