package com.example.cleanapplication.mvi

import com.example.cleanapplication.model.RepositoryListModel
import com.example.cleanapplication.network.Failure

data class EffectWrapperAction(val effect: Effect) : Action

interface RepositoryAction : Action {
    data class Search(val query: String) : RepositoryAction
}

interface RepositoryEffect : Effect {
    data class StartLoading(val query: String?) : RepositoryEffect
    data class ErrorLoading(override val error: Failure) : RepositoryEffect, Fail
    data class SuccessLoading(val data: RepositoryListModel) : RepositoryEffect
}

interface RepositoryNews : News {
    data class ErrorLoading(override val error: Failure) : RepositoryNews, Fail
}