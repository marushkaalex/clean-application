package com.example.cleanapplication.mvi

class MainStore(
        initialState: MainState,
        actor: Actor<MainState, Action, Effect>,
        reducer: Reducer<MainState, Effect>,
        newsPublisher: NewsPublisher<MainState, Action, Effect, News>
    ) : BaseStore<MainState, Action, Effect, News>(initialState, actor, reducer, newsPublisher)