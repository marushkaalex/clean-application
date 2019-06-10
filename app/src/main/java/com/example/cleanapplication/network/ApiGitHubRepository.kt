package com.example.cleanapplication.network

import com.example.cleanapplication.model.RepositoryListModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ApiGitHubRepository(private val api: IApi) : IGitHubRepository {

    override suspend fun getRepositories(page: Long, query: String?) {
        api.searchRepositories().exec().unseal<RepositoryListModel> {

        }?.let {

        }
    }
}