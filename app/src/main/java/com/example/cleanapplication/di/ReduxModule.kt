package com.example.cleanapplication.di

import com.example.cleanapplication.MainActivity
import com.example.cleanapplication.mvi.*
import com.example.cleanapplication.network.IApi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module // todo @ActivityScope
class ReduxModule {

    @Provides
    fun provideCoroutineScope(activity: MainActivity): CoroutineScope = activity

    @Provides
    fun provideActor(scope: CoroutineScope, api: IApi): IActorProvider<MainState, Action, Effect> =
        object : IActorProvider<MainState, Action, Effect> {
            override fun getActor(): Actor<MainState, Action, Effect> = RepositoryActor(scope, api)
        }

    @Provides
    fun provideReducer(): IReducerProvider<MainState, Effect> =
        object : IReducerProvider<MainState, Effect> {
            override fun getReducer(): Reducer<MainState, Effect> = RepositoryReducer()
        }


    @Provides
    fun provideNewsPublisher(): INewPublisherProvider<MainState, Action, Effect, News> =
        object : INewPublisherProvider<MainState, Action, Effect, News> {
            override fun getNewsPublisher(): NewsPublisher<MainState, Action, Effect, News> = RepositoryNewsPublisher()
        }

    @Provides
    fun provideMainStore(
        actorProvider: IActorProvider<MainState, Action, Effect>,
        reducerProvider: IReducerProvider<MainState, Effect>,
        newsPublisherProvider: INewPublisherProvider<MainState, Action, Effect, News>
    ): MainStore = MainStore(
        initialState = MainState(),
        actor = actorProvider.getActor(),
        reducer = reducerProvider.getReducer(),
        newsPublisher = newsPublisherProvider.getNewsPublisher()
    )

    class MainStore(
        initialState: MainState,
        actor: Actor<MainState, Action, Effect>,
        reducer: Reducer<MainState, Effect>,
        newsPublisher: NewsPublisher<MainState, Action, Effect, News>
    ) : BaseStore<MainState, Action, Effect, News>(initialState, actor, reducer, newsPublisher)
}