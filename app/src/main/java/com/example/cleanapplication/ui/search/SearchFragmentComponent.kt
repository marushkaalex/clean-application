package com.example.cleanapplication.ui.search

import com.example.cleanapplication.di.interaction.Injector
import com.example.cleanapplication.di.annotation.FragmentScope
import com.example.cleanapplication.ui.activity.IActivityComponent
import dagger.Component

@FragmentScope
@Component(modules = [SearchFragmentModule::class], dependencies = [IActivityComponent::class])
interface SearchFragmentComponent : Injector<SearchFragment>