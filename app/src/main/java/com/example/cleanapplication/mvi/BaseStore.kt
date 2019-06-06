package com.example.cleanapplication.mvi

import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

open class BaseStore<Action, State>(
    initialState: State,
    private val reducer: Reducer<State, Action>
) : IStore<Action, State> {

    private val channel = BroadcastChannel<State>(Channel.CONFLATED).also { it.offer(initialState) }
    private var state = initialState

    fun subscribe() = channel.openSubscription()

    suspend fun dispatch(action: Action) {
        val newState = reducer(state, action)
        if (newState != state) {
            state = newState
            channel.send(state)
        }
    }
}