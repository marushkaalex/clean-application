package com.example.cleanapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class ScopedActivity : AppCompatActivity(), CoroutineScope {

    protected val job = Job()
    override val coroutineContext = job + Dispatchers.IO

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
