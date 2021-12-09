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
        return oldMoviesList[oldItemPosition] == newList[newItemPosition]
    }
}