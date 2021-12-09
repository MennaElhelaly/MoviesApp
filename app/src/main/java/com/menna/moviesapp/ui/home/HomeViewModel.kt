package com.menna.moviesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.menna.moviesapp.data_layer.entity.Category
import com.menna.moviesapp.data_layer.entity.Movies
import com.menna.moviesapp.data_layer.entity.Result
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
    val movies = MutableLiveData<List<Result>>()
    val network = MutableLiveData<Boolean?>()
    var page = 1
    val categoriesList = listOf(Category("Top Rated",1,false),
        Category("Now Playing",2,false),
        Category("Upcoming",3,false),
        Category("Popular",4,false))
    private val handler = CoroutineExceptionHandler { _, exception ->
        network.postValue(true)
    }
    init {
       getTopRatedMovies()
    }
    private fun getNowPlayingMovies() {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getNowPlayingMovies()
            response?.let {
                movies.postValue(it.results)
                progressBar.postValue(true)
            }
        }
    }
    private fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getPopularMovies()
            response?.let {
                movies.postValue(it.results)
                progressBar.postValue(true)
            }
        }
    }
    private fun getTopRatedMovies() {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getTopRatedMovies()
            response?.let {
                movies.postValue(it.results)
                progressBar.postValue(true)
            }
        }
    }
    private fun getUpcomingMovies() {
        CoroutineScope(Dispatchers.IO + handler).launch {
            progressBar.postValue(false)
            val response = apiRepository.getUpcomingMovies()
            response?.let {
                movies.postValue(it.results)
                progressBar.postValue(true)
            }
        }
    }
    fun getData(item: Category){
        item.selected =true
        page = item.page
        when (page) {
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
    fun editCategorySelected(){
       categoriesList[page-1].selected =true
    }

}