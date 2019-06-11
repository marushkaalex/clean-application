package com.example.cleanapplication.mvi

import com.example.cleanapplication.network.Failure
import kotlinx.coroutines.channels.ReceiveChannel

interface IStore<Wish, State, News> {
    val state: State
    fun openStateSubscription(): ReceiveChannel<State>
    fun openNewsSubscription(): ReceiveChannel<News>
    suspend fun dispatch(wish: Wish)
}

interface Action
interface Effect
interface Fail {
    val error: Failure?
}

interface News

typealias Actor<State, Action, Effect> = (state: State, action: Action) -> ReceiveChannel<Effect>
typealias Reducer<State, Effect> = (state: State, action: Effect) -> State
typealias NewsPublisher<State, Action, Effect, News> = (action: Action, effect: Effect, state: State) -> News?
typealias Middleware<In, Out> = (`in`: In) -> Out