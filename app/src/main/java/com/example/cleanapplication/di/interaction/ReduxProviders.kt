package com.example.cleanapplication.di.interaction

import com.example.mvicore.Actor
import com.example.mvicore.NewsPublisher
import com.example.mvicore.Reducer

interface IActorProvider<State, Action, Effect> {
    fun getActor(): Actor<State, Action, Effect>
}

interface IReducerProvider<State, Effect> {
    fun getReducer(): Reducer<State, Effect>
}

interface INewPublisherProvider<State, Action, Effect, News> {
    fun getNewsPublisher(): NewsPublisher<State, Action, Effect, News>
}