package com.menna.moviesapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.menna.moviesapp.data_layer.entity.Category
import com.menna.moviesapp.data_layer.entity.MovieDetails
import com.menna.moviesapp.data_layer.entity.Result
import com.menna.moviesapp.data_layer.remote_sourse.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val apiRepository: RemoteDataSource) : ViewModel() {

    val progressBar = MutableLiveData<Boolean>()
    val movie = MutableLiveData<MovieDetails>()
    val network = MutableLiveData<Boolean?>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        network.postValue(true)
    }

    fun getNowPlayingMovies(id:Int) {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getMovieDetails(id)
            response?.let {
                movie.postValue(it)
                progressBar.postValue(true)
            }
        }
    }


}