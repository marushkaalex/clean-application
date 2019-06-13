package com.example.cleanapplication.ui.search

import android.arch.lifecycle.MutableLiveData
import com.example.cleanapplication.util.cancelJob
import com.example.cleanapplication.mvi.MainStore
import com.example.cleanapplication.mvi.RepositoriesStatus
import com.example.cleanapplication.mvi.RepositoryAction
import com.example.cleanapplication.ui.vm.ScopedViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ObsoleteCoroutinesApi
class SearchFragmentViewModel @Inject constructor(
    private val store: MainStore
) : ScopedViewModel() {

    val results = MutableLiveData<String>()

    private var searchJob: Job? = null


    init {
        launch {
            store.openStateSubscription().consumeEach {
                results.postValue(when (it.repositoriesState.status) {
                    RepositoriesStatus.LOADING -> "Loading..."
                    RepositoriesStatus.SHOW_DATA -> it.repositoriesState.repositories.items.joinToString { repo -> repo.name }
                    RepositoriesStatus.INIT -> "Init"
                    RepositoriesStatus.ERROR -> "Error"
                })
            }
        }
    }

    fun search(query: String) {
        searchJob.cancelJob()
        searchJob = launch {
            store.dispatch(RepositoryAction.Search(query))
        }
    }
}