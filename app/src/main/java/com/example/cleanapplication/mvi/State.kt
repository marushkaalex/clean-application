package com.example.cleanapplication.mvi

import com.example.cleanapplication.model.RepositoryListModel

data class State(val repositoriesState: RepositoriesState = RepositoriesState())

data class RepositoriesState(val repositories: RepositoryListModel = RepositoryListModel(), val query: String? = null)