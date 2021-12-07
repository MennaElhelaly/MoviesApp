package com.menna.moviesapp.data_layer.remote_sourse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MovieService {
    private const val BASE_URL = "https://api.themoviedb.org/3/movie/550?"
    private val client = OkHttpClient.Builder().build()
        //api_key=c50f5aa4e7c95a2a553d29b81aad6dd0
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val MOVIE_SERVICE: MovieApi = getRetrofit()
        .create(MovieApi::class.java)
}