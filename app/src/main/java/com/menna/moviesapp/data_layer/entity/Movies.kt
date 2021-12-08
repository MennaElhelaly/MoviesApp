package com.menna.moviesapp.data_layer.entity

data class Movies(
    val dates: Dates?,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
data class Category(
        val name: String,
        val page: Int
)