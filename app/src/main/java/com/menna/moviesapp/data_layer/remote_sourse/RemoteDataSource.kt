package com.menna.moviesapp.data_layer.remote_sourse

import android.util.Log
import com.menna.moviesapp.data_layer.entity.Movies
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    suspend fun getNowPlayingMovies(): Movies?{
        val response = MovieService.movieService.getNowPlayingMovies(apiKey)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.i("Menna", "getNowPlayingMovies response " + it)
                    return it
                }
            } else {
                Log.i("Menna", "getNowPlayingMovies response failuer " + response.errorBody().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("Menna", "getNowPlayingMovies error? " + e.printStackTrace())
        }
        return null
    }
    suspend fun getPopularMovies(): Movies?{
        val response = MovieService.movieService.getPopularMovies(apiKey)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.i("Menna", "getPopularMovies response " + it)
                    return it
                }
            } else {
                Log.i("Menna", "getPopularMovies response failuer " + response.errorBody().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("Menna", "getPopularMovies error? " + e.printStackTrace())
        }
        return null
    }
    companion object{
        const val apiKey = "c50f5aa4e7c95a2a553d29b81aad6dd0"
    }
}