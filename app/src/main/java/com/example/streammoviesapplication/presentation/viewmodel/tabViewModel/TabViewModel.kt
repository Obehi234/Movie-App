package com.example.streammoviesapplication.presentation.viewmodel.tabViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.data.model.localData.TVSeriesEntity
import com.example.streammoviesapplication.data.repository.IDocumentaryRepository
import com.example.streammoviesapplication.data.repository.IMoviesDetailsRepository
import com.example.streammoviesapplication.data.repository.IMoviesTabRepository
import com.example.streammoviesapplication.data.repository.ITVSeriesRepository
import com.example.streammoviesapplication.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabViewModel @Inject constructor(
    val repository: IMoviesTabRepository,
    val tvSeriesRepository: ITVSeriesRepository,
    val documentaryRepository: IDocumentaryRepository
) : ViewModel() {

    private val _moviesTabLiveData = MutableLiveData<Resource<List<MovieResultEntity>>>()
    val moviesTabLiveData: LiveData<Resource<List<MovieResultEntity>>> = _moviesTabLiveData

    private val _tvSeriesLiveData = MutableLiveData<Resource<List<TVSeriesEntity>>>()
    val tvSeriesLiveData: LiveData<Resource<List<TVSeriesEntity>>> = _tvSeriesLiveData

    init {
        fetchTabMovies()
        fetchTVSeries()
        fetchDocumentary()
    }

    private fun fetchTVSeries() {
        viewModelScope.launch {
            try {
                _tvSeriesLiveData.value = Resource.Loading()

                tvSeriesRepository.fetchTVSeries().collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _tvSeriesLiveData.value = result
                            Log.d("VM_TV_SERIES", "${result.data}")
                        }

                        is Resource.Error -> {
                            showError()
                            Log.d("CHECK_TV_ERROR", "${result.message}")
                        }

                        else -> {
                            Log.d("CHECK_TV_SERIES", "${result.message}")
                        }
                    }
                }
            } catch (e: Exception) {
                showError()
                Log.e("CHECK_TV_ERROR", "Exception: ${e.message}")
            }
        }
    }


    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> = _movieId

    fun setMovieId(id: Int) {
        _movieId.value = id
    }

    fun fetchTabMovies() {
        viewModelScope.launch {
            _moviesTabLiveData.value = Resource.Loading()
            try {
                repository.fetchMoviesListTab().collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _moviesTabLiveData.value = Resource.Success(result.data)
                            Log.d("CHECK_DETAILS", "${result.data}")
                        }

                        is Resource.Error -> {
                            showError()
                            Log.d("CHECK_DETAILS", "${result.message}")
                        }

                        else -> {
                            Log.d("CHECK_DETAILS", "${result.message}")
                        }

                    }


                }

            } catch (e: Exception) {
                _moviesTabLiveData.value = Resource.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }

    private fun fetchDocumentary() {
        viewModelScope.launch {

        }
    }

    private fun showError() {
        Log.d("CHECK_ERROR", "NOT SET")
    }

}