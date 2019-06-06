package com.example.cleanapplication.mvi

import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class ScopedActivity : AppCompatActivity(), CoroutineScope {

    protected val job = Job()
    override val coroutineContext = job + Dispatchers.IO

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
