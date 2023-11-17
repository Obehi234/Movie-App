package com.example.streammoviesapplication.presentation.viewmodel.tabViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streammoviesapplication.data.model.localData.MovieResultEntity
import com.example.streammoviesapplication.data.repository.IMoviesTabRepository
import com.example.streammoviesapplication.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabViewModel @Inject constructor(
    val repository: IMoviesTabRepository
) : ViewModel() {


    private val _moviesTabLiveData = MutableLiveData<Resource<List<MovieResultEntity>>>()
    val moviesTabLiveData: LiveData<Resource<List<MovieResultEntity>>> = _moviesTabLiveData

    init {
        fetchTabMovies()
    }
     fun fetchTabMovies() {
         Log.d("CHECK_TAB","")
        viewModelScope.launch {
            _moviesTabLiveData.value = Resource.Loading()
            try {
                 repository.fetchMoviesListTab().collect{result ->
                     when(result){
                         is Resource.Success -> {
                             _moviesTabLiveData.value = Resource.Success(result.data)
                             Log.d("CHECK_VM_RESULT", "${result.data}")
                         }

                         is Resource.Error -> {
                             Log.d("CHECK ERROR IN VM", "DATA DISPLAY ERROR")
                         }

                         else -> {
                             Log.d("CHECK_LOADING", "NOT SET")
                         }

                     }


                }

            } catch (e: Exception) {
                _moviesTabLiveData.value = Resource.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }

}