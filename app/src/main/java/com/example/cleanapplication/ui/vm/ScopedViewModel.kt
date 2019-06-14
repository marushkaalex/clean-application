package com.example.cleanapplication.ui.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class ScopedViewModel : ViewModel(), CoroutineScope {
    protected val viewModelJob = Job()
    override val coroutineContext: CoroutineContext = viewModelJob + Dispatchers.IO

    override fun onCleared() {
        viewModelJob.cancel()
    }
}