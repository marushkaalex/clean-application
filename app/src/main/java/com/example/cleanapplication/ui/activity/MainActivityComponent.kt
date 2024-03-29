package com.example.cleanapplication.ui.activity

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.example.cleanapplication.MainActivity
import com.example.cleanapplication.di.interaction.Injector
import com.example.cleanapplication.di.module.*
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Singleton

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@Singleton
@Component(
    modules = [ReduxModule::class, NetworkModule::class, NetworkModule::class,
        ConstantsModule::class, ViewModelModule::class, ActivityModule::class]
)
interface MainActivityComponent : IActivityComponent<MainActivity> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance activity: MainActivity): MainActivityComponent
    }
}

interface IActivityComponent<T : Activity> : Injector<T> {
    fun fragmentViewModelFactory(): ViewModelProvider.Factory
}