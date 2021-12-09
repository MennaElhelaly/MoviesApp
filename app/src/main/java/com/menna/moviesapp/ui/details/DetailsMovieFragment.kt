package com.menna.moviesapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.menna.moviesapp.R
import com.menna.moviesapp.data_layer.entity.Result
import com.menna.moviesapp.databinding.DetailsMovieFragmentBinding


class DetailsMovieFragment : Fragment() {
    private lateinit var binding: DetailsMovieFragmentBinding
    private val args: DetailsMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsMovieFragmentBinding.inflate(layoutInflater)
        bindData(args.movie)
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun bindData(movie: Result) {
        binding.name.text = movie.title
        binding.rate.text = movie.vote_average.toString()
        binding.releaseYear.text = movie.release_date
        binding.overviewValue.text = movie.overview
        binding.languageValue.text = movie.original_language
        binding.originalTitle.text = movie.original_title
        if (movie.adult){
            binding.adultValue.text = getString(R.string.yes)
        }else{
            binding.adultValue.text = getString(R.string.no)
        }
        val imageLink = "https://image.tmdb.org/t/p/w200" + movie.backdrop_path
        Glide.with(requireContext()).load(imageLink).into(binding.image)
    }

}