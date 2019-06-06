package com.example.cleanapplication.mvi

open class ReducerFeature<Wish : Any, State : Any>(
    initialState: State,
    reducer: Reducer<State, Wish>
) {


}