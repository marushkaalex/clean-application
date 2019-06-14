package com.example.cleanapplication.ui.search

import com.example.cleanapplication.model.RepositoryModel
import com.example.viewcore.adapter.CompositeAdapter
import com.example.viewcore.adapter.cast
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @Provides
    fun provideTest(): String = "test"

    @Provides
    fun provideAdapter() = CompositeAdapter().apply {
        addComponent<RepositoryModel>(SearchRepositoryItemAdapterComponent().cast())
    }
}