package com.example.cleanapplication.di.module

import com.example.cleanapplication.BuildConfig
import com.example.cleanapplication.di.interaction.IBuildConfigInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ConstantsModule {

    @Provides
    @Singleton
    fun provideBuildConfigInteractor(): IBuildConfigInteractor = object :
        IBuildConfigInteractor {
        override val isDebug = BuildConfig.DEBUG
        override val baseUrl = BuildConfig.BASE_API_URL
    }
}