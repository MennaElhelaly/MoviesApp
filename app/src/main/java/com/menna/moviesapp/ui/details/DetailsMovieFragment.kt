package com.menna.moviesapp.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.menna.moviesapp.R

class DetailsMovieFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsMovieFragment()
    }

    private lateinit var viewModel: DetailsMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsMovieViewModel::class.java)
        // TODO: Use the ViewModel
    }

}