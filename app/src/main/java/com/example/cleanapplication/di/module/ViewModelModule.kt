package com.example.cleanapplication.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanapplication.di.FragmentViewModelFactory
import com.example.cleanapplication.di.annotation.ViewModelKey
import com.example.cleanapplication.ui.search.SearchFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentViewModel::class)
    abstract fun bindSearchFragmentViewModel(viewModel: SearchFragmentViewModel): ViewModel


    @Binds
    abstract fun bindFragmentViewModelFactory(factory: FragmentViewModelFactory): ViewModelProvider.Factory
}