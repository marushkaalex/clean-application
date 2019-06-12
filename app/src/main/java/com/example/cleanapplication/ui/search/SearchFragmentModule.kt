package com.example.cleanapplication.ui.search

import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @Provides
    fun provideTest(): String = "test"
}