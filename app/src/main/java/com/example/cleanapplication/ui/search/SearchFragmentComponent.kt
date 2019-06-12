package com.example.cleanapplication.ui.search

import com.example.cleanapplication.di.Injector
import com.example.cleanapplication.di.scopes.FragmentScope
import com.example.cleanapplication.ui.activity.IActivityComponent
import com.example.cleanapplication.ui.activity.MainActivityComponent
import dagger.Component

@FragmentScope
@Component(modules = [SearchFragmentModule::class], dependencies = [IActivityComponent::class])
interface SearchFragmentComponent : Injector<SearchFragment>