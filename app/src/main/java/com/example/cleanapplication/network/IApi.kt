package com.example.cleanapplication.network

import com.example.cleanapplication.model.RepositoryListModel
import retrofit2.Call
import retrofit2.http.GET

interface IApi {
    @GET("search/repositories")
    fun searchRepositories(): Call<RepositoryListModel>
}