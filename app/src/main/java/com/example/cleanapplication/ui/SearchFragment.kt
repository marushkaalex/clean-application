package com.example.cleanapplication.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.cleanapplication.di.Injector
import com.example.cleanapplication.di.cast
import com.example.cleanapplication.ui.search.DaggerSearchFragmentComponent
import javax.inject.Inject

class SearchFragment : BaseDaggerFragment() {

    @Inject
    lateinit var test: String

    override fun getInjector(): Injector<Fragment> = DaggerSearchFragmentComponent.create().cast()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("kek $test")
    }
}