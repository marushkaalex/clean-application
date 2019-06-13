package com.example.cleanapplication.mvi

import com.example.mvicore.Actor
import com.example.mvicore.BaseStore
import com.example.mvicore.NewsPublisher
import com.example.mvicore.Reducer
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainStore(
    initialState: MainState,
    actor: Actor<MainState, Action, Effect>,
    reducer: Reducer<MainState, Effect>,
    newsPublisher: NewsPublisher<MainState, Action, Effect, News>
    ) : BaseStore<MainState, Action, Effect, News>(initialState, actor, reducer, newsPublisher)