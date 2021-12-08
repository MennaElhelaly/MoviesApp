package com.menna.moviesapp.data_layer.remote_sourse

import com.menna.moviesapp.data_layer.entity.Movies
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {

    @GET("/3/movie/now_playing?")
    suspend fun getNowPlayingMovies(@Query("api_key") api_key: String): Response<Movies>
    @GET("/3/movie/popular?")
    suspend fun getPopularMovies(@Query("api_key") api_key: String): Response<Movies>

}