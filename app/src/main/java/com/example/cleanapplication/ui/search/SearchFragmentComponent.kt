package com.example.cleanapplication.ui.search

import com.example.cleanapplication.di.Injector
import com.example.cleanapplication.ui.SearchFragment
import dagger.Component

@Component(modules = [SearchFragmentModule::class])
interface SearchFragmentComponent : Injector<SearchFragment>