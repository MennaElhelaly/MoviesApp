package com.menna.moviesapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.menna.moviesapp.data_layer.entity.Category
import com.menna.moviesapp.data_layer.entity.Movies
import com.menna.moviesapp.data_layer.remote_sourse.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiRepository: RemoteDataSource) :
    ViewModel() {

    val progressBar = MutableLiveData<Boolean>()
    val movies = MutableLiveData<Movies>()
    val network = MutableLiveData<Boolean?>()
    val categoriesList = listOf(Category("Top Rated",1), Category("Now Playing",2),Category("Upcoming",3),
    Category("Popular",4) )
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Menna", "CoroutineExceptionHandler got +++++++++++++++++++++++++++ $exception")
        network.postValue(false)
    }
    init {
       getTopRatedMovies()
    }

    private fun getNowPlayingMovies() {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getNowPlayingMovies()
            response?.let {
                movies.postValue(it)
                progressBar.postValue(true)
            }
        }
    }

    private fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getPopularMovies()
            response?.let {
                movies.postValue(it)
            }
        }
    }

    private fun getTopRatedMovies() {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getTopRatedMovies()
            response?.let {
                movies.postValue(it)
            }
        }
    }

    private fun getUpcomingMovies() {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getUpcomingMovies()
            response?.let {
                movies.postValue(it)
            }
        }
    }
    fun getData(num:Int){
        when (num) {
            1 -> {
                getTopRatedMovies()
            }
            2 -> {
                getNowPlayingMovies()
            }
            3 -> {
                getUpcomingMovies()
            }
            else -> {
                getPopularMovies()
            }
        }
    }

}