package com.example.cleanapplication.di

import com.example.cleanapplication.mvi.Actor
import com.example.cleanapplication.mvi.NewsPublisher
import com.example.cleanapplication.mvi.Reducer

interface IActorProvider<State, Action, Effect> {
    fun getActor(): Actor<State, Action, Effect>
}

interface IReducerProvider<State, Effect> {
    fun getReducer(): Reducer<State, Effect>
}

interface INewPublisherProvider<State, Action, Effect, News> {
    fun getNewsPublisher(): NewsPublisher<State, Action, Effect, News>
}