package com.example.cleanapplication.mvi

interface Action {
}

interface CounterAction : Action {
    object IncreaseCounter : CounterAction
    object RefreshCounter : CounterAction
}