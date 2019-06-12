package com.example.cleanapplication

import android.os.Bundle
import com.example.cleanapplication.di.DaggerMainActivityComponent
import com.example.cleanapplication.di.ReduxModule
import com.example.cleanapplication.mvi.EventSourceActivity
import com.example.cleanapplication.ui.SearchFragment
import javax.inject.Inject

class MainActivity : EventSourceActivity<Any>() {

    @Inject
    lateinit var store: ReduxModule.MainStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val component = DaggerMainActivityComponent.builder().activity(this).build()
        component.inject(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment())
            .commitAllowingStateLoss()
    }
}

fun kek(t: Any?) = println("kek $t")