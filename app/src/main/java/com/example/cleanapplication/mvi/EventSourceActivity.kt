package com.example.cleanapplication.mvi

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

@ExperimentalCoroutinesApi
open class EventSourceActivity<T> : ScopedActivity() {

    private val eventChannel = BroadcastChannel<T>(1)

    fun subscribe() = eventChannel.openSubscription()

    suspend fun nextEvent(event: T) = eventChannel.send(event)
}