package com.example.cleanapplication.ui.search

import android.arch.lifecycle.MutableLiveData
import com.example.cleanapplication.di.interaction.IToaster
import com.example.cleanapplication.mvi.MainStore
import com.example.cleanapplication.mvi.RepositoriesStatus
import com.example.cleanapplication.mvi.RepositoryAction
import com.example.cleanapplication.mvi.RepositoryNews
import com.example.cleanapplication.network.getMessage
import com.example.cleanapplication.ui.vm.ScopedViewModel
import com.example.cleanapplication.util.cancelJob
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import javax.inject.Inject

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class SearchFragmentViewModel
@Inject constructor(
    private val store: MainStore,
    private val toaster: IToaster
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
        launch {
            store.openNewsSubscription().consumeEach {
                if (it is RepositoryNews.ErrorLoading) {
                    withContext(Dispatchers.Main) {
                        toaster.showLongToast(it.error.getMessage())
                    }
                }
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