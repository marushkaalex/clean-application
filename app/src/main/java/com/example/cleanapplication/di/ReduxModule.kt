package com.example.cleanapplication.di

import com.example.cleanapplication.MainActivity
import com.example.cleanapplication.mvi.*
import com.example.cleanapplication.network.IApi
import kotlinx.coroutines.CoroutineScope

class ReduxModule {

    fun provideCoroutineScope(activity: MainActivity): CoroutineScope = activity

    fun provideActor(scope: CoroutineScope, api: IApi): IActorProvider<MainState, Action, Effect> =
        object : IActorProvider<MainState, Action, Effect> {
            override fun getActor(): Actor<MainState, Action, Effect> = RepositoryActor(scope, api)
        }

    fun provideReducer(): IReducerProvider<MainState, Effect> =
        object : IReducerProvider<MainState, Effect> {
            override fun getReducer(): Reducer<MainState, Effect> = RepositoryReducer()
        }


    fun provideNewsPublisher(): INewPublisherProvider<MainState, Action, Effect, News> =
        object : INewPublisherProvider<MainState, Action, Effect, News> {
            override fun getNewsPublisher(): NewsPublisher<MainState, Action, Effect, News> = RepositoryNewsPublisher()
        }

    fun provideMainStore(
        actorProvider: IActorProvider<MainState, Action, Effect>,
        reducerProvider: IReducerProvider<MainState, Effect>,
        newsPublisherProvider: INewPublisherProvider<MainState, Action, Effect, News>
    ): IStore<Action, MainState, News> = BaseStore(
        initialState = MainState(),
        actor = actorProvider.getActor(),
        reducer = reducerProvider.getReducer(),
        newsPublisher = newsPublisherProvider.getNewsPublisher()
    )
}