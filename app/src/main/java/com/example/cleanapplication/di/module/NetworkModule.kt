package com.example.cleanapplication.di.module

import com.example.cleanapplication.BuildConfig
import com.example.cleanapplication.di.interaction.IBuildConfigInteractor
import com.example.cleanapplication.network.IApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BASIC })
            }
        }
        .build()

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        buildConfigInteractor: IBuildConfigInteractor,
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(buildConfigInteractor.baseUrl)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): IApi = retrofit.create(IApi::class.java)
}