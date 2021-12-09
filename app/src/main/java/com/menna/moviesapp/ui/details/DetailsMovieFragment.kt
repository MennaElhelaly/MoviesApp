package com.menna.moviesapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.menna.moviesapp.R
import com.menna.moviesapp.data_layer.entity.MovieDetails
import com.menna.moviesapp.data_layer.remote_sourse.RemoteDataSource
import com.menna.moviesapp.databinding.DetailsMovieFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {
    @Inject lateinit var repository: RemoteDataSource
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: DetailsMovieFragmentBinding
    private val args: DetailsMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsMovieFragmentBinding.inflate(layoutInflater)
        getMovie(args.movie.id)

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun getMovie(id: Int) {
        viewModel.getNowPlayingMovies(id)
        viewModel.movie.observe(viewLifecycleOwner,{
            it?.let {
                bindData(it)
            }
        })
    }

    private fun bindData(movie: MovieDetails) {
        binding.name.text = movie.title
        binding.rate.text = movie.vote_average.toString()
        binding.releaseYear.text = movie.release_date
        binding.overviewValue.text = movie.overview
        binding.languageValue.text = movie.original_language
        binding.originalTitle.text = movie.original_title
        binding.typeValue.text=movie.genres[0].name
        if (movie.adult){
            binding.adultValue.text = getString(R.string.yes)
        }else{
            binding.adultValue.text = getString(R.string.no)
        }
        val imageLink = "https://image.tmdb.org/t/p/w200" + movie.backdrop_path
        Glide.with(requireContext()).load(imageLink).into(binding.image)
    }
    override fun onResume() {
        super.onResume()
        viewModel.progressBar.observe(this.requireActivity()) {
            if (it){
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.VISIBLE
            }
        }
        viewModel.network.observe(this.requireActivity()) {
            it?.let {
                if (it){
                    Toast.makeText(requireContext(), getString(R.string.network_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onStop() {
        viewModel.network.postValue(null)
        super.onStop()
    }

}