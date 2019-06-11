package com.example.cleanapplication.mvi

//class Feature1 : ReducerFeature<Feature1.Wish, Feature1.MainState>(initialState = MainState(), reducer = ReducerImpl()) {
//
//    data class MainState(val count: Int = 0)
//
//    sealed class Wish {
//        object IncrementCounter : Wish()
//    }

//    class ReducerImpl : Reducer<MainState, Wish> {
//        override fun invoke(state: MainState, effect: Wish): MainState = when (effect) {
//            Wish.IncrementCounter -> state.copy(count = state.count + 1)
//        }
//    }
//}