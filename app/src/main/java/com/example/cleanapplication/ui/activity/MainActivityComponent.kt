package com.example.cleanapplication.ui.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import com.example.cleanapplication.MainActivity
import com.example.cleanapplication.di.interaction.Injector
import com.example.cleanapplication.di.module.ConstantsModule
import com.example.cleanapplication.di.module.NetworkModule
import com.example.cleanapplication.di.module.ReduxModule
import com.example.cleanapplication.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ReduxModule::class, NetworkModule::class, NetworkModule::class, ConstantsModule::class, ViewModelModule::class])
interface MainActivityComponent : IActivityComponent<MainActivity> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance activity: MainActivity): MainActivityComponent
    }
}

interface IActivityComponent<T : Activity> : Injector<T> {
    fun fragmentViewModelFactory(): ViewModelProvider.Factory
}