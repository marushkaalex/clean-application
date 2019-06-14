package com.example.cleanapplication.ui

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class ScopedFragment : Fragment(), CoroutineScope {

    protected val fragmentJob = Job()
    override val coroutineContext: CoroutineContext = fragmentJob + Dispatchers.Default

    fun hasActiveJobs() = fragmentJob.children.firstOrNull { it.isActive }?.run { true } ?: false

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        fragmentJob.cancel()
    }
}