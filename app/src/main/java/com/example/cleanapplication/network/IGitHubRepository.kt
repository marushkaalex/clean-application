package com.example.cleanapplication.network

interface IGitHubRepository {

    suspend fun getRepositories(page: Long, query: String? = null)
}