package com.example.cleanapplication.mvi

//class Feature1 : ReducerFeature<Feature1.Wish, Feature1.State>(initialState = State(), reducer = ReducerImpl()) {
//
//    data class State(val count: Int = 0)
//
//    sealed class Wish {
//        object IncrementCounter : Wish()
//    }

//    class ReducerImpl : Reducer<State, Wish> {
//        override fun invoke(state: State, effect: Wish): State = when (effect) {
//            Wish.IncrementCounter -> state.copy(count = state.count + 1)
//        }
//    }
//}