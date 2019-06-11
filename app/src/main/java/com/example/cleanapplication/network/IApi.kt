package com.example.cleanapplication.network

import com.example.cleanapplication.model.RepositoryListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApi {
    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String): Call<RepositoryListModel>
}