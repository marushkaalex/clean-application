package com.example.cleanapplication.mvi

//val reducer: Reducer<State, Action> = { state, action ->
//    when (action) {
//        is RepositoryAction -> handleRepository(state, action)
//        else -> state
//    }
//}
//
//fun handleRepository(oldState: State, action: RepositoryAction): State = when (action) {
//    is RepositoryAction.Search -> oldState.copy(repositoriesState = oldState.repositoriesState.copy(repositories = action.results))
//    else -> oldState
//}