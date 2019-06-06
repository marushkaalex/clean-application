package com.example.cleanapplication.mvi

typealias Reducer<State, Action> = suspend (state: State, action: Action) -> State

val coreReducer: Reducer<CoreState, Action> = { state, action ->
    when (action) {
        CounterAction.IncreaseCounter -> state.copy(counter = state.counter + 1)
        CounterAction.RefreshCounter -> state.copy(counter = 0)
        else -> state
    }
}