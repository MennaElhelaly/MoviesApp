package com.menna.moviesapp.di

import com.menna.moviesapp.data_layer.entity.Result
import com.menna.moviesapp.data_layer.remote_sourse.RemoteDataSource
import com.menna.moviesapp.ui.home.adapters.MoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object AppModule {
    @Provides
    fun provideRemoteSource() : RemoteDataSource{
        return RemoteDataSource()
    }

}