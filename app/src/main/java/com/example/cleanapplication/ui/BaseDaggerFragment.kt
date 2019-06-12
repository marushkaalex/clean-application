package com.example.cleanapplication.ui

import android.content.Context
import android.support.v4.app.Fragment
import com.example.cleanapplication.di.Injector

abstract class BaseDaggerFragment : ScopedFragment() {

    abstract fun getInjector(): Injector<Fragment>

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getInjector().inject(this)
    }
}