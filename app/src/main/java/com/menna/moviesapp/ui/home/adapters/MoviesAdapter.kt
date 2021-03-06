package com.menna.moviesapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menna.moviesapp.R
import com.menna.moviesapp.data_layer.entity.Result
import com.menna.moviesapp.ui.home.adapters.diffutil.MyDiffUtil
import javax.inject.Inject


class MoviesAdapter @Inject constructor(
        private var moviesList: List<Result>,
        private var listener : OnClickMovieListener
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = moviesList[position].title
        holder.title.isSelected = true
        holder.rate.text = moviesList[position].vote_average.toString()
        moviesList[position].poster_path?.let {
            val imageLink = "https://image.tmdb.org/t/p/w200" + it
            Glide.with(holder.image.context).load(imageLink).into(holder.image)
        }
        if (moviesList[position].poster_path.isNullOrEmpty()){
            holder.image.setImageResource(R.drawable.empty)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
                R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view), View.OnClickListener {

        var title: TextView = view.findViewById(R.id.movie_title)
        var rate: TextView = view.findViewById(R.id.rate)
        var image: ImageView = view.findViewById(R.id.image)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0) {

                itemView -> {
                    listener.onMovieClick(moviesList[adapterPosition])
                }

            }
        }

    }

    interface OnClickMovieListener {
        fun onMovieClick(item: Result)
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
    fun setData(list: List<Result>) {
        val diffUtil = MyDiffUtil(moviesList,list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        moviesList = list
        diffResult.dispatchUpdatesTo(this)
    }
}