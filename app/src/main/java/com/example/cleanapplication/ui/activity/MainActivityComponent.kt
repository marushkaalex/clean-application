package com.example.cleanapplication.ui.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import com.example.cleanapplication.MainActivity
import com.example.cleanapplication.di.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ReduxModule::class, NetworkModule::class, NetworkModule::class, ConstantsModule::class, ViewModelModule::class])
interface MainActivityComponent : IActivityComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance activity: MainActivity): MainActivityComponent
    }
}

interface IActivityComponent : Injector<Activity> {
    fun fragmentViewModelFactory(): ViewModelProvider.Factory
}