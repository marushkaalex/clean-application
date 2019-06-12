package com.example.cleanapplication.ui.vm

import android.arch.lifecycle.ViewModel
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