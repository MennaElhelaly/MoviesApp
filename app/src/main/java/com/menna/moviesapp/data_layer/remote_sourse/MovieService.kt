package com.menna.moviesapp.data_layer.remote_sourse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MovieService {
    private const val BASE_URL = "https://api.themoviedb.org"
    private val client = OkHttpClient.Builder().build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val movieService: MovieApi = getRetrofit()
        .create(MovieApi::class.java)
}