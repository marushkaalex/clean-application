package com.example.cleanapplication

import android.app.Activity
import android.os.Bundle
import com.example.cleanapplication.di.interaction.Injector
import com.example.cleanapplication.di.interaction.cast
import com.example.cleanapplication.mvi.MainStore
import com.example.cleanapplication.ui.BaseDaggerActivity
import com.example.cleanapplication.ui.activity.DaggerMainActivityComponent
import com.example.cleanapplication.ui.activity.IActivityComponent
import com.example.cleanapplication.ui.search.SearchFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainActivity : BaseDaggerActivity() {

    override var component: IActivityComponent<*> = DaggerMainActivityComponent.factory().create(this)

    override fun getInjector(): Injector<in Activity> = component.cast()

    @Inject
    lateinit var store: MainStore

    @ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment())
            .commitAllowingStateLoss()

        launch {
            store.openStateSubscription().consumeEach {
                kek(it)
            }
        }
    }
}

fun kek(t: Any?) = println("kek $t")