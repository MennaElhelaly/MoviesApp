package com.menna.moviesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.menna.moviesapp.R
import com.menna.moviesapp.data_layer.entity.Category
import com.menna.moviesapp.data_layer.entity.Result
import com.menna.moviesapp.databinding.FragmentHomeBinding
import com.menna.moviesapp.ui.home.adapters.CategoryAdapter
import com.menna.moviesapp.ui.home.adapters.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),CategoryAdapter.OnClickCategoryListener,MoviesAdapter.OnClickMovieListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var categoriesList: List<Category>


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        //homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        categoryAdapter = CategoryAdapter(emptyList(),this)
        moviesAdapter = MoviesAdapter(emptyList(),this)
        categoriesList = listOf(Category("Now Playing",1),Category("Popular",2))
        categoryAdapter.setData(categoriesList)

        initUI()
        dataObservers()
        homeViewModel.getNowPlayingMovies()
        return binding.root
    }

    private fun dataObservers() {
        homeViewModel.nowPlayingMovies.observe(viewLifecycleOwner,{
            it?.let {
                moviesAdapter.setData(it.results)
            }
        })
        homeViewModel.popularMovies.observe(viewLifecycleOwner,{
            it?.let {
                moviesAdapter.setData(it.results)
            }
        })
    }

    private fun initUI() {
        binding.recyclerCategories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
        binding.recyclerMovies.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = moviesAdapter
        }
    }

    override fun onCategoryClick(item: Category) {
        when (item.page) {
            1 -> {
                homeViewModel.getNowPlayingMovies()
            }
            2 -> {
                homeViewModel.getPopularMovies()
            }
            else -> {
                //homeViewModel.getNowPlayingMovies()
            }
        }

    }

    override fun onMovieClick(item: Result) {

    }
}