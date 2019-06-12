package com.example.cleanapplication.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.cleanapplication.ui.search.SearchFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentViewModel::class)
    abstract fun bindSearchFragmentViewModel(viewModel: SearchFragmentViewModel): ViewModel


    @Binds
    abstract fun bindFragmentViewModelFactory(factory: FragmentViewModelFactory): ViewModelProvider.Factory
}