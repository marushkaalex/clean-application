package com.example.cleanapplication.di

import com.example.cleanapplication.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ReduxModule::class, NetworkModule::class, NetworkModule::class, ConstantsModule::class])
interface MainActivityComponent {

    //    fun provideMainStore(): ReduxModule.MainStore
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: MainActivity): Builder

        fun build(): MainActivityComponent
    }
}