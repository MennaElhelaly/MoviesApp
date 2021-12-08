package com.menna.moviesapp.ui.home.adapters

import androidx.recyclerview.widget.DiffUtil
import com.menna.moviesapp.data_layer.entity.Result

class MyDiffUtil(
        private var oldMoviesList: List<Result>,
        private var newList: List<Result>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldMoviesList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldMoviesList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldMoviesList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldMoviesList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }
            oldMoviesList[oldItemPosition].poster_path != newList[newItemPosition].poster_path -> {
                false
            }
            oldMoviesList[oldItemPosition].overview != newList[newItemPosition].overview -> {
                false
            }
            oldMoviesList[oldItemPosition].adult != newList[newItemPosition].adult -> {
                false
            }
            oldMoviesList[oldItemPosition].backdrop_path != newList[newItemPosition].backdrop_path -> {
                false
            }
            oldMoviesList[oldItemPosition].release_date != newList[newItemPosition].release_date -> {
                false
            }
            oldMoviesList[oldItemPosition].adult != newList[newItemPosition].adult -> {
                false
            }
            oldMoviesList[oldItemPosition].video != newList[newItemPosition].video -> {
                false
            }
            oldMoviesList[oldItemPosition].vote_average != newList[newItemPosition].vote_average -> {
                false
            }
            oldMoviesList[oldItemPosition].vote_count != newList[newItemPosition].vote_count -> {
                false
            }
            oldMoviesList[oldItemPosition].popularity != newList[newItemPosition].popularity -> {
                false
            }
            else -> true
        }
    }
}