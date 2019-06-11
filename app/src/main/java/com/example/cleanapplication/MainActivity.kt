package com.example.cleanapplication

import android.os.Bundle
import com.example.cleanapplication.di.DaggerMainActivityComponent
import com.example.cleanapplication.di.ReduxModule
import com.example.cleanapplication.mvi.EventSourceActivity
import com.example.cleanapplication.mvi.RepositoryAction
import com.example.cleanapplication.mvi.RepositoryNews
import com.example.cleanapplication.network.Error
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : EventSourceActivity<Any>() {

    @Inject
    lateinit var store: ReduxModule.MainStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val component = DaggerMainActivityComponent.builder().activity(this).build()
        component.inject(this)
        launch {
            launch {
                store.openStateSubscription().consumeEach {
                    kek("state: $it")
                }
            }
            launch {
                store.openNewsSubscription().consumeEach {
                    kek("news: ${((it as RepositoryNews.ErrorLoading).error as? Error)?.data?.string()}")
                }
            }
            store.dispatch(RepositoryAction.Search("123"))
        }
    }
}

fun kek(t: Any?) = println("kek $t")