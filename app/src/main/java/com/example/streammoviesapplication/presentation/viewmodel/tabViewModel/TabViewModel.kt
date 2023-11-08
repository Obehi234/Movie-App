package com.example.streammoviesapplication.presentation.viewmodel.tabViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.data.model.localData.MovieViewState
import com.example.streammoviesapplication.data.repository.IMoviesTabRepository
import com.example.streammoviesapplication.data.repository.MoviesTabRepositoryImpl
import com.example.streammoviesapplication.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabViewModel @Inject constructor(
    val repository: IMoviesTabRepository
) : ViewModel() {

    private val _moviesTabLiveData = MutableLiveData<Resource<List<MovieResultEntity>>>()
    val moviesTabLiveData: LiveData<Resource<List<MovieResultEntity>>> = _moviesTabLiveData


    private fun fetchTabMovies(id: Int) {
        viewModelScope.launch {
            _moviesTabLiveData.value = Resource.Loading()
            try {
                val result = repository.fetchMoviesListTab(id).first()
                _moviesTabLiveData.value = result
                Log.d("CHECK_VM_RESULT", "${result.data}")
            } catch (e: Exception) {
                _moviesTabLiveData.value = Resource.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }

}