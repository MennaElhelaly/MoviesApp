package com.menna.moviesapp.data_layer.remote_sourse

import android.util.Log
import com.menna.moviesapp.data_layer.entity.MovieDetails
import com.menna.moviesapp.data_layer.entity.Movies
import javax.inject.Inject


class RemoteDataSource @Inject constructor() {
    suspend fun getNowPlayingMovies(): Movies?{
        val response = MovieService.movieService.getNowPlayingMovies(apiKey)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            } else {
                Log.i("Remote", "getNowPlayingMovies response failuer " + response.errorBody().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("Remote", "getNowPlayingMovies error? " + e.printStackTrace())
        }
        return null
    }
    suspend fun getPopularMovies(): Movies?{
        val response = MovieService.movieService.getPopularMovies(apiKey)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            } else {
                Log.i("Remote", "getPopularMovies response failuer " + response.errorBody().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("Remote", "getPopularMovies error? " + e.printStackTrace())
        }
        return null
    }
    suspend fun getTopRatedMovies(): Movies?{
        val response = MovieService.movieService.getTopRatedMovies(apiKey)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            } else {
                Log.i("Remote", "getTopRatedMovies response failuer " + response.errorBody().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("Remote", "getTopRatedMovies error? " + e.printStackTrace())
        }
        return null
    }
    suspend fun getUpcomingMovies(): Movies?{
        val response = MovieService.movieService.getUpcomingMovies(apiKey)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            } else {
                Log.i("Remote", "getUpcomingMovies response failuer " + response.errorBody().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("Remote", "getUpcomingMovies error? " + e.printStackTrace())
        }
        return null
    }
    suspend fun getMovieDetails(id:Int): MovieDetails?{
        val response = MovieService.movieService.getMovieDetails(id,apiKey)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            } else {
                Log.i("Remote", "getMovieDetails response failuer " + response.errorBody().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("Remote", "getMovieDetails error? " + e.printStackTrace())
        }
        return null
    }
    companion object{
        const val apiKey = "c50f5aa4e7c95a2a553d29b81aad6dd0"
    }
}