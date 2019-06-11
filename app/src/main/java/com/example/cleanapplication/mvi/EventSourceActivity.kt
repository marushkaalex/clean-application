package com.example.cleanapplication.mvi

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel

@ExperimentalCoroutinesApi
open class EventSourceActivity<T> : ScopedActivity() {

    private val eventChannel = BroadcastChannel<T>(1)

    fun subscribe() = eventChannel.openSubscription()

    suspend fun nextEvent(event: T) = eventChannel.send(event)
}