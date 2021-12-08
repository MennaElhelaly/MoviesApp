package com.menna.moviesapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.menna.moviesapp.data_layer.entity.Movies
import com.menna.moviesapp.data_layer.remote_sourse.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    var progressBar = MutableLiveData<Boolean>()
    var nowPlayingMovies = MutableLiveData<Movies>()
    var popularMovies = MutableLiveData<Movies>()
    var apiRepository: RemoteDataSource = RemoteDataSource()
    var network = MutableLiveData<Boolean?>()
    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Menna", "CoroutineExceptionHandler got +++++++++++++++++++++++++++ $exception")
        network.postValue(false)
    }

    fun  getNowPlayingMovies(){
        CoroutineScope(Dispatchers.IO + handler).launch {
            val response = apiRepository.getNowPlayingMovies()
            response?.let {
                nowPlayingMovies.postValue(it)
                progressBar.postValue(true)
            }
        }
    }
    fun  getPopularMovies(){
        CoroutineScope(Dispatchers.IO + handler).launch {
            val response = apiRepository.getPopularMovies()
            response?.let {
                popularMovies.postValue(it)
                progressBar.postValue(true)
            }
        }
    }

}