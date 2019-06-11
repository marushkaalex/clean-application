package com.example.cleanapplication.mvi

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
open class BaseStore<State, Action, Effect, News>(
    initialState: State,
    private val actor: Actor<State, Action, Effect>,
    private val reducer: Reducer<State, Effect>,
    private val newsPublisher: NewsPublisher<State, Action, Effect, News>
) : IStore<Action, State, News> {

    override var state = initialState
    private val stateChannel = BroadcastChannel<State>(Channel.CONFLATED).also { it.offer(state) }
    private val newsChannel = BroadcastChannel<News>(Channel.CONFLATED)
    override fun openStateSubscription(): ReceiveChannel<State> = stateChannel.openSubscription()
    override fun openNewsSubscription(): ReceiveChannel<News> = newsChannel.openSubscription()

    override suspend fun dispatch(wish: Action) {
        actor(state, wish).consumeEach { effect ->
            val newState = reducer(state, effect)
            if (newState != state) {
                state = newState
                stateChannel.send(state)
                newsPublisher(wish, effect, newState)
            }
        }
    }
}