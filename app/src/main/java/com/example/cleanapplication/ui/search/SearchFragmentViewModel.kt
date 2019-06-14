package com.example.cleanapplication.ui.search

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
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

    val items = ObservableArrayList<Any>()
    val isInProgress = ObservableBoolean(false)
    private var searchJob: Job? = null

    init {
        launch {
            store.openStateSubscription().consumeEach {
                when (it.repositoriesState.status) {
                    RepositoriesStatus.SHOW_DATA -> {
                        items.clear()
                        items.addAll(it.repositoriesState.repositories.items)
                    }
                    else -> {
                    }
                }
                isInProgress.set(it.repositoriesState.status == RepositoriesStatus.LOADING)
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