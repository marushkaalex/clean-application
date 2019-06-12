package com.example.cleanapplication.ui.search

import com.example.cleanapplication.kek
import com.example.cleanapplication.mvi.MainStore
import com.example.cleanapplication.ui.vm.ScopedViewModel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    store: MainStore
) : ScopedViewModel() {

    init {
        launch {
            store.openStateSubscription().consumeEach {
                kek("vm ${it.repositoriesState.query}")
            }
        }
    }
}