package com.example.cleanapplication.di

import com.example.cleanapplication.model.RepositoryListModel
import com.example.cleanapplication.mvi.*
import com.example.cleanapplication.network.IApi
import com.example.cleanapplication.network.exec
import com.example.cleanapplication.network.unseal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce


@ExperimentalCoroutinesApi
class RepositoryActor constructor(private val scope: CoroutineScope, private val api: IApi) :
    Actor<MainState, Action, Effect> {
    override fun invoke(state: MainState, action: Action): ReceiveChannel<Effect> = scope.produce {
        when (action) {
            is RepositoryAction.Search -> handleSearch(action.query)
            is EffectWrapperAction -> send(action.effect)
        }
    }

    private suspend fun ProducerScope<Effect>.handleSearch(query: String) {
        send(RepositoryEffect.StartLoading(query))
        api.searchRepositories(query).exec().unseal<RepositoryListModel> {
            send(RepositoryEffect.ErrorLoading(it))
        }?.let {
            send(RepositoryEffect.SuccessLoading(it))
        }
        close()
    }
}

class RepositoryReducer : Reducer<MainState, Effect> {
    override fun invoke(state: MainState, action: Effect): MainState = when (action) {
        is RepositoryEffect.StartLoading -> state.copy(
            repositoriesState = state.repositoriesState.copy(
                query = action.query,
                status = RepositoriesStatus.LOADING
            )
        )
        is RepositoryEffect.SuccessLoading -> state.copy(
            repositoriesState = state.repositoriesState.copy(
                repositories = action.data,
                status = RepositoriesStatus.SHOW_DATA
            )
        )
        is RepositoryEffect.ErrorLoading -> state.copy(repositoriesState = state.repositoriesState.copy(status = RepositoriesStatus.ERROR))
        else -> state
    }
}

class RepositoryNewsPublisher : NewsPublisher<MainState, Action, Effect, News> {
    override fun invoke(action: Action, effect: Effect, state: MainState): News? {
        return if (effect is RepositoryEffect.ErrorLoading) RepositoryNews.ErrorLoading(effect.error) else null
    }
}