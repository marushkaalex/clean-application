package com.example.cleanapplication.mvi

import com.example.cleanapplication.model.RepositoryListModel

data class MainState(val repositoriesState: RepositoriesState = RepositoriesState())

data class RepositoriesState(val repositories: RepositoryListModel = RepositoryListModel(), val query: String? = null, val status: RepositoriesStatus = RepositoriesStatus.INIT)

enum class RepositoriesStatus {
    INIT,
    LOADING,
    ERROR,
    SHOW_DATA
}