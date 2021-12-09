package com.menna.moviesapp.data_layer.remote_sourse

import com.menna.moviesapp.data_layer.entity.MovieDetails
import com.menna.moviesapp.data_layer.entity.Movies
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {

    @GET("/3/movie/now_playing?")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): Response<Movies>
    @GET("/3/movie/popular?")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<Movies>
    @GET("/3/movie/top_rated?")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): Response<Movies>
    @GET("/3/movie/upcoming?")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): Response<Movies>
    @GET("/3/movie/{movie_id}?")
    suspend fun getMovieDetails(@Path("movie_id") id:Int,@Query("api_key") apiKey: String): Response<MovieDetails>

}