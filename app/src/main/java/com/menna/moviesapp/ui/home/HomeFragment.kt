package com.menna.moviesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.menna.moviesapp.R
import com.menna.moviesapp.data_layer.entity.Category
import com.menna.moviesapp.data_layer.entity.Result
import com.menna.moviesapp.data_layer.remote_sourse.RemoteDataSource
import com.menna.moviesapp.databinding.FragmentHomeBinding
import com.menna.moviesapp.ui.details.DetailsMovieFragmentDirections
import com.menna.moviesapp.ui.home.adapters.CategoryAdapter
import com.menna.moviesapp.ui.home.adapters.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(),CategoryAdapter.OnClickCategoryListener,MoviesAdapter.OnClickMovieListener {

    @Inject lateinit var repository: RemoteDataSource
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var moviesAdapter: MoviesAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        categoryAdapter = CategoryAdapter(emptyList(),this)
        moviesAdapter = MoviesAdapter(emptyList(),this)
        categoryAdapter.setData(homeViewModel.categoriesList)

        initUI()
        dataObservers()
        return binding.root
    }

    private fun dataObservers() {
        homeViewModel.movies.observe(viewLifecycleOwner,{
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
        homeViewModel.getData(item)
        categoryAdapter.notifyDataSetChanged()
    }

    override fun onMovieClick(item: Result) {
        val action =DetailsMovieFragmentDirections.actionNavigationHomeToDetailsMovieFragment(item)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.progressBar.observe(this.requireActivity()) {
            if (it){
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.VISIBLE
            }
        }
        homeViewModel.network.observe(this.requireActivity()) {
            it?.let {
                if (it){
                    Toast.makeText(requireContext(), getString(R.string.network_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        homeViewModel.editCategorySelected()
        super.onStart()
    }

    override fun onStop() {
        homeViewModel.network.postValue(null)
        super.onStop()
    }

}
