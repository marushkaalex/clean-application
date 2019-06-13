package com.example.cleanapplication.ui

import android.app.Activity
import android.os.Bundle
import com.example.cleanapplication.di.Injector
import com.example.cleanapplication.ui.activity.IActivityComponent
import com.example.cleanapplication.ui.activity.MainActivityComponent
import com.example.cleanapplication.ui.activity.ScopedActivity

abstract class BaseDaggerActivity : ScopedActivity() {

    abstract fun getInjector(): Injector<in Activity>
    abstract var component: IActivityComponent<*>
        protected set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInjector().inject(this)
    }
}